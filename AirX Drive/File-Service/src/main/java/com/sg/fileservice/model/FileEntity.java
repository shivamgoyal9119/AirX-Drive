package com.sg.fileservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long size;
    private Long folderInfo;
    private String path;

    public FileEntity() {};

    public FileEntity(Long id, String name, Long size, Long folderInfo, String path) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.folderInfo = folderInfo;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getFolderInfo() {
        return folderInfo;
    }

    public void setFolderInfo(Long folderInfo) {
        this.folderInfo = folderInfo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
