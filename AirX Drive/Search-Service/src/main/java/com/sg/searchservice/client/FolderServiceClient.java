package com.sg.searchservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "Folder-Service", url = "http://localhost:8082")
public interface FolderServiceClient {

    @GetMapping("/api/folders")
    List<Map<String, Object>> getAllFolder();

    @GetMapping("/api/folders/parent/{parentId}")
    List<Map<String, Object>> getAllFilesByParentId(@PathVariable Long parentId);

}
