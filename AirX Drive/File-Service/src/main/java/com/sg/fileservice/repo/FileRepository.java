package com.sg.fileservice.repo;

import com.sg.fileservice.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    public List<FileEntity> findByFolderInfo(Long id);
}
