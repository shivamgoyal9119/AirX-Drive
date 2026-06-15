package com.sg.searchservice.client;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FileServiceClientFallBack implements FileServiceClient{

    @Override
    public List<Map<String, Object>> getAllFiles() {
        System.out.println("File Service Is Unavailable ... ");
        return List.of();
    }

    @Override
    public List<Map<String, Object>> getAllFilesByFolderId(Long folderId) {
        System.out.println("File Service Is Not Working ... ");
        return List.of();
    }
}
