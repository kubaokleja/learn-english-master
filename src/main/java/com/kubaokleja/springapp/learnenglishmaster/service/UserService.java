package com.kubaokleja.springapp.learnenglishmaster.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kubaokleja.springapp.learnenglishmaster.entity.User;
import com.kubaokleja.springapp.learnenglishmaster.user.GameUser;

public interface UserService extends UserDetailsService{
	public void save(GameUser user);
	public User findByUsername(String username);
	public List<User> findAll();
	public void deleteById(int theId);
	public List<User> searchUsername(String username);
	public int processScoreAndReturnBestScore(String username, int score);
}
