package com.fossgen.healthcare.AidXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fossgen.healthcare.AidXpert.model.FileModel;

@Repository
public interface FileRepo extends JpaRepository<FileModel, String> {

}
