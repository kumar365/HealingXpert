package com.healthcare.HealingXpert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.HealingXpert.model.AuditUser;
import com.healthcare.HealingXpert.repository.AuditUserRepository;

@Service
@Transactional
public class AuditUserService {

	@Autowired
	AuditUserRepository auditRepository;

	public void createUser(AuditUser auditUser) {
		auditRepository.save(auditUser);
	}

}
