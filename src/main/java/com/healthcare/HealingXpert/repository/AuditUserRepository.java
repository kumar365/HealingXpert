package com.healthcare.HealingXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.HealingXpert.model.AuditUser;

public interface AuditUserRepository extends JpaRepository<AuditUser, String> {

}