package com.healthcare.healingxpert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.healingxpert.enums.ERole;
import com.healthcare.healingxpert.model.Role;
import com.healthcare.healingxpert.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Role findByName(ERole eRole) {
		return roleRepository.findByName(eRole);
	}

}
