package com.healthcare.HealingXpert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.HealingXpert.enums.ERole;
import com.healthcare.HealingXpert.model.Role;
import com.healthcare.HealingXpert.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Role findByName(ERole eRole) {
		return roleRepository.findByName(eRole);
	}

}
