package com.healthcare.HealingXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.HealingXpert.model.FileModel;

@Repository
public interface FileRepo extends JpaRepository<FileModel, String> {

}
