package com.kubaokleja.springapp.learnenglishmaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.kubaokleja.springapp.learnenglishmaster.entity.User;


@Repository
@RepositoryRestResource(path="members")
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	List<User>findByUsernameContaining(String username);
}
