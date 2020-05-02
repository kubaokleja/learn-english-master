package com.kubaokleja.springapp.learnenglishmaster.entity;



import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="game_history")

public class GameHistory {
	//define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@NotNull
	@Column(name="game_time")	
	private Timestamp gameTime;
	
	@NotNull
	@Column(name="game_score")
	private int gameScore;

	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH})
	
	@JoinColumn(name="user_id")
	private User user;
	//define constructors
	public GameHistory() {
		
	}

	public GameHistory(@NotNull Timestamp gameTime, @NotNull int gameScore) {
		super();
		this.gameTime = gameTime;
		this.gameScore = gameScore;
	}


	public GameHistory(@NotNull Timestamp gameTime, @NotNull int gameScore, User user) {
		super();
		this.gameTime = gameTime;
		this.gameScore = gameScore;
		this.user = user;
	}

	public Timestamp getGameTime() {
		return gameTime;
	}



	public void setGameTime(Timestamp gameTime) {
		this.gameTime = gameTime;
	}



	public int getGameScore() {
		return gameScore;
	}



	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GameHistory [id=" + id + ", gameTime=" + gameTime + ", gameScore=" + gameScore + ", user=" + user + "]";
	}

	
}
