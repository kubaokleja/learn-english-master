package com.kubaokleja.springapp.learnenglishmaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kubaokleja.springapp.learnenglishmaster.entity.GameHistory;
@Repository
public interface GameHistoryRepository extends JpaRepository<GameHistory, Integer> {

	List<GameHistory> findAll();
	
}
