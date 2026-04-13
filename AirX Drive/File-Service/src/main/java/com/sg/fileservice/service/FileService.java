package com.sg.fileservice.service;

import com.sg.fileservice.model.FileEntity;
import com.sg.fileservice.repo.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<FileEntity> getAllFile(){
        return fileRepository.findAll();
    }

    public FileEntity getFileById(Long id){
        return fileRepository.findById(id)
                .orElse(null);
    }

    public FileEntity createFile(@RequestBody FileEntity file){
        return fileRepository.save(file);
    }

    public void deleteFile(Long id){
        fileRepository.deleteById(id);
    }

    public List<FileEntity> getFilesByFolderInfo(Long folderId){
        return fileRepository.findByFolderInfo(folderId);
    }
}
