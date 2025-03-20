package com.example.pet_project_mem_generation;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
@SessionScope
public class Mem_table {
	
	private final AccountRepository account;
	private int count;
	private String first_name;
	private List<Mem> mems = new ArrayList<>();
	
	public Mem_table(AccountRepository account) {
		this.account = account;
	}
	
	public void addMem(Mem mem) {mems.add(mem);}
	public void setName(String first_name) {this.first_name = first_name;}
	public void setCount(int count) {this.count = count;}
	public void addCount() {this.count += 1;}
	
	public List<Mem> getAllMems() {return mems;}
	public String getName() {return first_name;}
	public int getCount() {return count;}
	public int capacity() {return mems.size();}
	
	public void destroy() {mems.clear();}
	
	public List<Account> findAccountsbyName(String name) {
		return account.findAccountsbyName(name);
	}
	
	public void addPerson(String name, String mem, String like) {
		account.addPerson(name, mem, like);
	}
	
	public Iterable<Account> getAllAccounts() {
		return account.findAll();
	}
}
