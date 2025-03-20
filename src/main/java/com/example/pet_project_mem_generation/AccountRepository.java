package com.example.pet_project_mem_generation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jdbc.repository.query.Modifying;
import java.util.*;

public interface AccountRepository extends CrudRepository<Account, Long> {

	@Query("SELECT * from account WHERE first_name = :name")
	List<Account> findAccountsbyName(String name);
	
	@Modifying
	@Query("INSERT INTO account(first_name, mem_name, like_mem) VALUES (:name, :mem, :like)")
	void addPerson(String name, String mem, String like);
	
}
