package com.fossgen.healthcare.AidXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fossgen.healthcare.AidXpert.model.AuditUser;

public interface AuditUserRepository extends JpaRepository<AuditUser, String> {

}