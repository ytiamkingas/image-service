package com.image.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ImageControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testListImagesEndpoint() throws Exception {
		mockMvc.perform(get("/images")
				.header("Authorization", "Bearer dummyjwt"))
				.andExpect(status().isOk());
	}
}