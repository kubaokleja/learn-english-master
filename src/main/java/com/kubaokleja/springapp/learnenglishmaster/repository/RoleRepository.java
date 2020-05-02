package com.kubaokleja.springapp.learnenglishmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kubaokleja.springapp.learnenglishmaster.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findRoleByName(String roleName);

}
