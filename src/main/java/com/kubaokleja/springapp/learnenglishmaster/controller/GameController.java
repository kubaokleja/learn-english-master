package com.kubaokleja.springapp.learnenglishmaster.controller;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kubaokleja.springapp.learnenglishmaster.entity.Word;
import com.kubaokleja.springapp.learnenglishmaster.enums.Category;
import com.kubaokleja.springapp.learnenglishmaster.service.GameService;
import com.kubaokleja.springapp.learnenglishmaster.service.UserService;


@Controller
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	GameService gameService;
	@Autowired
	UserService userService;
	
	@GetMapping("")
	public String showGamePage() {
		return "game";
	}
	//ModelMap potem, hasło , lista odpowiedzi :) 
	// --- TEST --- 
	@GetMapping("/test")
	public String showTestPage(Model model) {
		Map <Word, List<Word>> quizMap = gameService.testMap();
		model.addAttribute("questions", quizMap);
		return "games/test";
	}
	
	@PostMapping("/test-result")
	public String showTestResult(HttpServletRequest request) {
		int score = 0;
		String questionIds[] = request.getParameterValues("questionId");
		for(String questionId: questionIds)
		{
			if(questionId.equals(request.getParameter(questionId))) {
				score++;
			}
		}
		User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = loggedUser.getUsername();
		gameService.saveGameHistoryToRepository(new Timestamp(System.currentTimeMillis()), score, username);
		int bestScore =  userService.processScoreAndReturnBestScore(username, score);
		String message = username+ " - best score: "+bestScore;
		request.setAttribute("message", message);
		request.setAttribute("score", score);
		return "games/test-result";
	}
	
	@GetMapping("/translator")
	public String showTranslatorPage(Model model) {
		Word wordToTranslate = gameService.getRandomWord();
		model.addAttribute("word", wordToTranslate);
		return "games/translator";
	}
	@GetMapping("/memory")
	public String showMemoryPage(Model model) {
		String [] memoryArray = gameService.memoryArray();
		List<String> list = Arrays.asList(memoryArray); 
		model.addAttribute("list", list);
		return "games/memory";
	}
	
	
	//--- HANGMAN ---
	@GetMapping("/hangman")
	public String showHangmanPage() {
		return "games/hangman";
	}
	@GetMapping("/passItWordIntoHangman")
	public String passItWordIntoHangman(Model model) {
		Word word = gameService.hangmanPassword(Category.IT);
		String password = word.getEnglishMeaning();
		String meaning = word.getPolishMeaning();
		model.addAttribute("password", password);
		model.addAttribute("meaning", meaning);
		return "games/hangman";
	}
	@GetMapping("/passGeneralWordIntoHangman")
	public String passGeneralWordIntoHangman(Model model) {
		Word word = gameService.hangmanPassword(Category.GENERAL);
		String password = word.getEnglishMeaning();
		String meaning = word.getPolishMeaning();
		model.addAttribute("password", password);
		model.addAttribute("meaning", meaning);
		return "games/hangman";
	}

}
