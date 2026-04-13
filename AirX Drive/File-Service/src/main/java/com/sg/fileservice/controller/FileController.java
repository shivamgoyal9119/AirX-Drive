package com.sg.fileservice.controller;

import com.sg.fileservice.model.FileEntity;
import com.sg.fileservice.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;
    private static final String UPLOAD_DIR = "uploads";

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<List<FileEntity>> getAllFile(){
        return ResponseEntity.ok(fileService.getAllFile());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileEntity> getFileById(@PathVariable Long id){
        return ResponseEntity.ok(fileService.getFileById(id));
    }

    @PostMapping
    public ResponseEntity<FileEntity> createFile(@RequestBody FileEntity fileEntity){
        return ResponseEntity.ok(fileService.createFile(fileEntity));
    }

    @DeleteMapping("/{id}")
    public void deleteFileById(@PathVariable Long id){
        fileService.deleteFile(id);
    }

    @GetMapping("/folder/{id}")
    public ResponseEntity<List<FileEntity>> getFilesByFolder(@PathVariable Long id){
        return ResponseEntity.ok(fileService.getFilesByFolderInfo(id));
    }

    @PostMapping("/upload")
    public Map<String, Object> uploadFile(@RequestParam("name") String name,
                                          @RequestParam("folderId") Long folderId,
                                          @RequestParam("file") MultipartFile file){

        try{
            Long fileSize = file.getSize();
            String fileName = file.getOriginalFilename();

            FileEntity fileEntity = new FileEntity();

            fileEntity.setId(System.currentTimeMillis());
            fileEntity.setName((fileName == null) ? name : fileName);
            fileEntity.setSize(fileSize);
            fileEntity.setFolderInfo(folderId);
            fileEntity.setPath("/files/"+fileEntity.getId());

            // Saving file disk
            String uploadDirPath = UPLOAD_DIR + File.separator + fileEntity.getId();
            Files.createDirectories(Paths.get(uploadDirPath));
            Path filePath = Paths.get(uploadDirPath, fileEntity.getName());
            Files.write(filePath, file.getBytes());

            FileEntity saved = fileService.createFile(fileEntity);

            return Map.of("Success", "TRUE", "File", saved);
        }catch (Exception e){
            return Map.of("Success", "FALSE", "Error", e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id){
        try{
            FileEntity fileEntity = fileService.getFileById(id);
            if(fileEntity == null){
                return ResponseEntity.noContent().build();
            }

            Path filePath = Paths.get(UPLOAD_DIR + id.toString() + fileEntity.getName());

            if(!Files.exists(filePath)){
                return ResponseEntity.noContent().build();
            }

            byte[] fileContent = Files.readAllBytes(filePath);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "Attachment : filename=/" + fileEntity.getName() + "/")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileContent.length))
                    .body(fileContent);

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error in downloading .... " + e.getMessage());
        }
    }

}
