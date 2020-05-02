package com.kubaokleja.springapp.learnenglishmaster.entity;

import java.sql.Timestamp;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;




@Entity
@Table(name="user")
public class User {
	//define fields
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private int id;
		
		@Column(name="username")
		private String username;
		
		@Column(name="email")
		private String email;
		
		@Column(name="password")
		private String password;
		
		@Column(name="best_score")
		private String bestScore;
		
		@Column(name="account_created")
		private Timestamp accountCreated;

		@ManyToMany(mappedBy = "users")
		private Collection<Role> roles;

		//define constructors
		public User() {
			
		}
		public User(String username,String password, String email, Timestamp accountCreated) {
			
			this.username = username;
			this.password=password;
			this.email = email;
			this.accountCreated=accountCreated;
		}
		

		public User(String username, String password,  String email, Timestamp accountCreated,
				Collection<Role> roles) {
			this.username = username;
			this.password = password;
			this.accountCreated=accountCreated;
			this.email = email;
			this.roles = roles;
		}
		//define getter/setter
		public int getId() {
			return id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Collection<Role> getRoles() {
			return roles;
		}
		public void setRoles(Collection<Role> roles) {
			this.roles = roles;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getBestScore() {
			return bestScore;
		}
		public void setBestScore(String bestScore) {
			this.bestScore = bestScore;
		}
		public Timestamp getAccountCreated() {
			return accountCreated;
		}
		public void setAccountCreated(Timestamp accountCreated) {
			this.accountCreated = accountCreated;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", email=" + email +"]";
		}
		
		
}
