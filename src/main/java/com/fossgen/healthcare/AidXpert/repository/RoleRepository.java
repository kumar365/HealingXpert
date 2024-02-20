package com.fossgen.healthcare.AidXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fossgen.healthcare.AidXpert.enums.ERole;
import com.fossgen.healthcare.AidXpert.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(ERole name);

}