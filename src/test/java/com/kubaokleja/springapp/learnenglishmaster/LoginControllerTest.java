package com.kubaokleja.springapp.learnenglishmaster;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "UserTest", password = "Password1", roles = "USER")
	public class LoginControllerTest {
	 
	  @Autowired
	  private MockMvc mvc;
	    
	  @Test
		public void showAccessDeniedPageTest() throws Exception {
			mvc.perform(get("/access-denied"))    
		      .andExpect(status().isOk())  
		      .andExpect(view().name("access-denied"));
		}
	
	  @Test
		public void showLoginPageTest() throws Exception {
			mvc.perform(get("/login"))    
		      .andExpect(status().isOk())  
		      .andExpect(view().name("login"));
		}
		
	}
