package com.healthcare.HealingXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.HealingXpert.enums.ERole;
import com.healthcare.HealingXpert.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(ERole name);

}