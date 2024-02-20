package com.fossgen.healthcare.AidXpert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fossgen.healthcare.AidXpert.model.AuditUser;
import com.fossgen.healthcare.AidXpert.repository.AuditUserRepository;

@Service
@Transactional
public class AuditUserService {

	@Autowired
	AuditUserRepository auditRepository;

	public void createUser(AuditUser auditUser) {
		auditRepository.save(auditUser);
	}

}
