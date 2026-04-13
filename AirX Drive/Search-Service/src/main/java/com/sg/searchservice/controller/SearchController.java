package com.sg.searchservice.controller;

import com.sg.searchservice.client.FileServiceClient;
import com.sg.searchservice.client.FolderServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private FolderServiceClient folderServiceClient;

    @Autowired
    private FileServiceClient fileServiceClient;

    @GetMapping
    public ResponseEntity<Map<String, Object>> search(@RequestParam String query){
        List<Map<String, Object>> allFile = fileServiceClient.getAllFiles();
        List<Map<String, Object>> allFolder = folderServiceClient.getAllFolder();

        List<Map<String, Object>> fileResult = allFile.stream()
                .filter(f -> f.get("name").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();

        List<Map<String, Object>> folderResult = allFolder.stream()
                .filter(f -> f.get("name").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();

        Map<String, Object> res = new HashMap<>();
        res.put("files", fileResult);
        res.put("folders", folderResult);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/files")
    public ResponseEntity<Map<String, Object>> searchFiles(@RequestParam String query){
        List<Map<String, Object>> allFile = fileServiceClient.getAllFiles();

        List<Map<String, Object>> fileResult = allFile.stream()
                .filter(f -> f.get("name").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();

        Map<String, Object> res = new HashMap<>();
        res.put("files", fileResult);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/folders")
    public ResponseEntity<Map<String, Object>> searchFolders(@RequestParam String query){
        List<Map<String, Object>> allFolder = folderServiceClient.getAllFolder();

        List<Map<String, Object>> folderResult = allFolder.stream()
                .filter(f -> f.get("name").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();

        Map<String, Object> res = new HashMap<>();
        res.put("folders", folderResult);

        return ResponseEntity.ok(res);
    }
}
