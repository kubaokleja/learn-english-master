package com.kubaokleja.springapp.learnenglishmaster;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.kubaokleja.springapp.learnenglishmaster.entity.User;
import com.kubaokleja.springapp.learnenglishmaster.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) 
public class UserRepositoryIntegrationTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Test
	public void whenFindByUsername_thenReturnUser() {
	    // given
	    User user = new User("test","Test1","test@test.pl", new Timestamp(System.currentTimeMillis()));
	    entityManager.persist(user);
	    entityManager.flush();
	 
	    // when
	    User found = userRepository.findByUsername(user.getUsername());
	 
	    // then
	    assertEquals(user.getUsername(), found.getUsername());
	}
}
