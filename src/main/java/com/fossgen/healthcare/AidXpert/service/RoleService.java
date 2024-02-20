package com.fossgen.healthcare.AidXpert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fossgen.healthcare.AidXpert.enums.ERole;
import com.fossgen.healthcare.AidXpert.model.Role;
import com.fossgen.healthcare.AidXpert.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Role findByName(ERole eRole) {
		return roleRepository.findByName(eRole);
	}

}
