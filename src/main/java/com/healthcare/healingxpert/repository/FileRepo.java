package com.healthcare.healingxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.healingxpert.model.FileModel;

@Repository
public interface FileRepo extends JpaRepository<FileModel, String> {

}
