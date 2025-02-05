package com.example.pet_project_mem_generation;

import org.springframework.data.annotation.Id;

public class Account {
	@Id
	public long Id;
	
	public String first_name;
	public String mem_name;
	public String like_mem;
}
