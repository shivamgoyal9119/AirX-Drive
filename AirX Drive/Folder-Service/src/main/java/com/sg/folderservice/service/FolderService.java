package com.sg.folderservice.service;

import com.sg.folderservice.model.FolderEntity;
import com.sg.folderservice.repo.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {

    private final FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public List<FolderEntity> getAllFolder(){
        return folderRepository.findAll();
    }

    public FolderEntity getFolderById(Long id){
        return folderRepository.findById(id)
                .orElse(null);
    }

    public FolderEntity createFolder(FolderEntity folder){
        return folderRepository.save(folder);
    }

    public void deleteFolder(Long id){
        folderRepository.deleteById(id);
    }

    public List<FolderEntity> getFoldersByParentId(Long id){
        return folderRepository.findByParentId(id);
    }


}
