package com.kubaokleja.springapp.learnenglishmaster.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kubaokleja.springapp.learnenglishmaster.entity.Role;
import com.kubaokleja.springapp.learnenglishmaster.entity.User;
import com.kubaokleja.springapp.learnenglishmaster.repository.RoleRepository;
import com.kubaokleja.springapp.learnenglishmaster.repository.UserRepository;
import com.kubaokleja.springapp.learnenglishmaster.user.GameUser;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public User findByUsername(String username) {
		// check the database if the user already exists
		return userRepository.findByUsername(username);
	}

	@Override
	@Transactional
	public List<User> findAll() {
		// check the database if the user already exists
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public void save(GameUser gameUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUsername(gameUser.getUsername());
		user.setPassword(passwordEncoder.encode(gameUser.getPassword()));
		user.setEmail(gameUser.getEmail());
		user.setAccountCreated(new Timestamp(System.currentTimeMillis()));
		user.setBestScore("0");
		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleRepository.findRoleByName("ROLE_USER")));

		 // save user in the database
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		userRepository.deleteById(theId);
	}

	@Override
	public List<User> searchUsername(String username) {
		
		return userRepository.findByUsernameContaining(username);
	}

	@Override
	public int processScoreAndReturnBestScore(String username, int score) {
		User currentUser = findByUsername(username);
		int bestScore = Integer.valueOf(currentUser.getBestScore());
		if(score > bestScore)
		{
			bestScore=score;
			currentUser.setBestScore(String.valueOf(bestScore));	
			userRepository.save(currentUser);
		}
		return bestScore;
	}

}
