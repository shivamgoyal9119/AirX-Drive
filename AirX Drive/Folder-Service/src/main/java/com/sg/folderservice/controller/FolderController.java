package com.sg.folderservice.controller;

import com.sg.folderservice.model.FolderEntity;
import com.sg.folderservice.service.FolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/folders")
public class FolderController {

    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping
    public ResponseEntity<List<FolderEntity>> getAllFolder(){
        return ResponseEntity.ok(folderService.getAllFolder());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FolderEntity> getFolderById(@PathVariable Long id){
        return ResponseEntity.ok(folderService.getFolderById(id));
    }

    @PostMapping
    public ResponseEntity<FolderEntity> createFolder(@RequestBody FolderEntity folder){
        return ResponseEntity.ok(folderService.createFolder(folder));
    }

    @DeleteMapping("/{id}")
    public void deleteFolderById(@PathVariable Long id){
        folderService.deleteFolder(id);
    }

    @GetMapping("/parent/{id}")
    public ResponseEntity<List<FolderEntity>> getAllFolderByParentId(@PathVariable Long id){
        return ResponseEntity.ok(folderService.getFoldersByParentId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createNewFolder(@RequestBody Map<String, Object> request){
        try{
            String name = (String) request.get("name");
            Long parentId = request.get("parentId") != null ?
                    Long.valueOf(request.get("parentId").toString()) : null;

            FolderEntity folder = new FolderEntity();
            folder.setName(name);
            folder.setId(System.currentTimeMillis());
            folder.setParentId(parentId);

            FolderEntity saved = folderService.createFolder(folder);

            return ResponseEntity.ok(Map.of("Success", "TRUE", "Folder", "Saved"));
        }catch (Exception e){
            return ResponseEntity.ok(Map.of("Success", "FALSE", "Error", e.getMessage()));
        }
    }
}
