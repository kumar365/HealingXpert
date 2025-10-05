package com.healthcare.healingxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.healingxpert.model.AuditUser;

public interface AuditUserRepository extends JpaRepository<AuditUser, String> {

}