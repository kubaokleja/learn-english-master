package com.kubaokleja.springapp.learnenglishmaster;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.kubaokleja.springapp.learnenglishmaster.config.CustomAuthenticationSuccessHandler;
import com.kubaokleja.springapp.learnenglishmaster.entity.User;
import com.kubaokleja.springapp.learnenglishmaster.repository.RoleRepository;
import com.kubaokleja.springapp.learnenglishmaster.repository.UserRepository;
import com.kubaokleja.springapp.learnenglishmaster.service.UserService;
import com.kubaokleja.springapp.learnenglishmaster.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserService.class)
public class UserServiceImplIntegrationTest {

	@TestConfiguration
    static class UserServiceImplTestContextConfiguration {
  
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }
 
    @Autowired
    private UserService userService;
 
    @MockBean
    private UserRepository userRepository;
    
   @MockBean
   private RoleRepository roleRepository;
   @MockBean
   private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
	@Test
	public void findByName_userFound() {
		User user = new User("test","Test1","test@test.pl", new Timestamp(System.currentTimeMillis()));
		when(userRepository.findByUsername("test")).thenReturn(user);
		assertEquals(user, userService.findByUsername("test"));
		verify(userRepository, times(1)).findByUsername("test");
	}
	
	@Test
	public void findByName_userNotFound() {
		when(userRepository.findByUsername("")).thenReturn(null);
		assertNull(userService.findByUsername(""));
	}
}
