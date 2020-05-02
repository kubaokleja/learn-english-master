package com.kubaokleja.springapp.learnenglishmaster.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.kubaokleja.springapp.learnenglishmaster.entity.Word;
import com.kubaokleja.springapp.learnenglishmaster.enums.Category;

public interface GameService {
	public Word hangmanPassword(Category category);
	public Map<Word, List<Word>> testMap();
	public void saveGameHistoryToRepository(Timestamp timestamp, int score, String username);
	public Word getRandomWord();
	public String[] memoryArray();
}
