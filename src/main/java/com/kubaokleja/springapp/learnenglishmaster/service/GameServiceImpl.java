package com.kubaokleja.springapp.learnenglishmaster.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kubaokleja.springapp.learnenglishmaster.entity.GameHistory;
import com.kubaokleja.springapp.learnenglishmaster.entity.User;
import com.kubaokleja.springapp.learnenglishmaster.entity.Word;
import com.kubaokleja.springapp.learnenglishmaster.enums.Category;
import com.kubaokleja.springapp.learnenglishmaster.repository.GameHistoryRepository;
import com.kubaokleja.springapp.learnenglishmaster.repository.WordRepository;

@Service
public class GameServiceImpl implements GameService{
	@Autowired
	WordRepository wordRepository;
	@Autowired
	GameHistoryRepository gameHistoryRepository;
	@Autowired
	UserService userService;
	Random rand = new Random();
	
	@Override
	public Word hangmanPassword(Category category) {
		List<Word> wordsByCategoryList = wordRepository.findByCategory(category) ;
	    Word randomWord = wordsByCategoryList.get(rand.nextInt(wordsByCategoryList.size()));
	    return randomWord;
	}

	@Override
	public Map<Word, List<Word>> testMap() {

		Map <Word, List<Word>> map = new HashMap<>();
		List<Word> allWords  = wordRepository.findAll();
		for(int i=0;i<20;i++) {
		    Word quizWord = allWords.get(rand.nextInt(allWords.size()));
		    
		    List<Word> answersInNotRandomOrder = new ArrayList<>();
		    
		    List<Word> answersInRandomOrder = new ArrayList<>();
		    
		    answersInNotRandomOrder.add(quizWord);
		    allWords.remove(quizWord);
		    for(int j=0;j<3;j++) {
		    	Word otherAnswer = allWords.get(rand.nextInt(allWords.size()));
		    	answersInNotRandomOrder.add(otherAnswer);
		    }
		    
		    for(int j=0;j<4;j++)
		    {
		    	Word answer = answersInNotRandomOrder.get(rand.nextInt(answersInNotRandomOrder.size()));
		    	answersInRandomOrder.add(answer);
		    	answersInNotRandomOrder.remove(answer);
		    }
		    map.put(quizWord, answersInRandomOrder);
		    
		}
		return map;
	}

	@Override
	public void saveGameHistoryToRepository(Timestamp timestamp, int score, String username) {
		User user = userService.findByUsername(username);
		GameHistory gameHistory = new GameHistory(timestamp, score, user);
		gameHistoryRepository.save(gameHistory);
	}

	@Override
	public Word getRandomWord() {
		List<Word> allWords  = wordRepository.findAll();
		Word quizWord = allWords.get(rand.nextInt(allWords.size()));
		return quizWord;
	}

	@Override
	public String[] memoryArray() {
		String[] memoryArray = new String[12];
		List<Word> allWords  = wordRepository.findAll();
		for(int i=0;i<6;i++) {
			 Word quizWord = allWords.get(rand.nextInt(allWords.size()));	 
			 memoryArray[i]=quizWord.getEnglishMeaning();
			 memoryArray[memoryArray.length-i-1]=quizWord.getPolishMeaning();
			 allWords.remove(quizWord);
		}
		return memoryArray;
	}
	

}
