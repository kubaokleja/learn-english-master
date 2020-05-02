package com.kubaokleja.springapp.learnenglishmaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kubaokleja.springapp.learnenglishmaster.entity.Word;
import com.kubaokleja.springapp.learnenglishmaster.enums.Category;

public interface WordRepository extends JpaRepository<Word, Integer> {
		List<Word> findByCategory(Category category);
}
