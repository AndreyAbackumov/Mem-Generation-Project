package com.example.pet_project_mem_generation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
class PetProjectMemGenerationApplicationTests {

	@Mock
	private Model model;
	
	@Mock
	private Mem_table mems;
	
	@InjectMocks
	private Image image;
	
	
	@Test
	void contextLoads_0() {
		
		boolean res = image.check_str("sdfsdf5");
		
		assertEquals(false, res);
	}
	
	@Test
	void contextLoads_1() {
		
		boolean res = image.check_str("fsdfsdfsd");
		
		assertEquals(true, res);
	}
	
	@Test
	void contextLoads_2() {

		String res = image.sendData("Ace-Primo", "", "", model);		
		assertEquals("redirect:/image?meme_name=Ace-Primo&top=&bottom=", res);
	}
	
	@Test
	void contextLoads_3() {

		String res = image.sendData("Ace-Primo", "hello", "", model);		
		assertEquals("redirect:/image?meme_name=Ace-Primo&top=hello&bottom=", res);
	}
	
	@Test
	void contextLoads_4() {

		String res = image.sendData("Ace-Primo", "", "hello", model);		
		assertEquals("redirect:/image?meme_name=Ace-Primo&top=&bottom=hello", res);
	}
	
	@Test
	void contextLoads_5() {

		String res = image.sendData("Ace-Primo", "hello", "bye", model);		
		assertEquals("redirect:/image?meme_name=Ace-Primo&top=hello&bottom=bye", res);
	}
	
}
