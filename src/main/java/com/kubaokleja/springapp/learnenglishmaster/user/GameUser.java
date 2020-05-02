package com.kubaokleja.springapp.learnenglishmaster.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.kubaokleja.springapp.learnenglishmaster.validation.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class GameUser {

	@Length(min = 4,max = 16 , message = "Username must have at least 4 characters and max 16")
	@NotBlank(message = "*Please provide an username")
	private String username;

	@Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,12}$",
	message = "Your password must be at least 4 characters, no more than 12 characters, and must include at least one upper case letter, one lower case letter, and one numeric digit.")
	@NotBlank(message = "*Please provide an password")
	private String password;
	
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,12}$",
	message = "Your password must be at least 4 characters, no more than 12 characters, and must include at least one upper case letter, one lower case letter, and one numeric digit.")
	@NotBlank(message = "*Please provide an password")
	private String matchingPassword;


	@Email(message = "*Please provide a valid Email")
	@NotBlank(message = "*Please provide an email")
	private String email;

	public GameUser() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
