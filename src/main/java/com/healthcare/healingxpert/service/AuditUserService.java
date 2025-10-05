package com.healthcare.healingxpert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.healingxpert.model.AuditUser;
import com.healthcare.healingxpert.repository.AuditUserRepository;

@Service
@Transactional
public class AuditUserService {

	@Autowired
	AuditUserRepository auditRepository;

	public void createUser(AuditUser auditUser) {
		auditRepository.save(auditUser);
	}

}
