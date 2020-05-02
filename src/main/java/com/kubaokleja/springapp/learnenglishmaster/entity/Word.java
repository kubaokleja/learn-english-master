package com.kubaokleja.springapp.learnenglishmaster.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kubaokleja.springapp.learnenglishmaster.enums.Category;


@Entity
@Table(name="word")
public class Word {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="polish")
	private String polishMeaning;
	
	@Column(name="english")
	private String englishMeaning;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name="category")
	private Category category;

	public Word() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPolishMeaning() {
		return polishMeaning;
	}

	public void setPolishMeaning(String polishMeaning) {
		this.polishMeaning = polishMeaning;
	}

	public String getEnglishMeaning() {
		return englishMeaning;
	}

	public void setEnglishMeaning(String englishMeaning) {
		this.englishMeaning = englishMeaning;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Word [id=" + id + ", polishMeaning=" + polishMeaning + ", englishMeaning=" + englishMeaning
				+ ", category=" + category + "]";
	}
	
	
	
}