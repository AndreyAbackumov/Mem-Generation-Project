package com.example.pet_project_mem_generation;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Image {
	
	public boolean check_str(String str) {
			
		boolean error = true;
			
		for(int i = 0; i < str.length(); ++i) {
			if (!(((str.charAt(i) >= 'A') && (str.charAt(i) <= 'Z'))||((str.charAt(i) >= 'a') && (str.charAt(i) <= 'z')))) {
				error = false;
			}
		}
			
		return error;
	}
	
	private final RestTemplate rest;
	private final Mem_table mems;
	
	private String Meme_name = null;
	
	public Image(RestTemplate rest, Mem_table mems) {
		this.rest = rest;
		this.mems = mems;
	}
	
	@GetMapping("/main")
	public String ShowMainPage() {
		return "main.html";
	}
	
	@PostMapping("/main")
	public String GetName(
			Model model,
			@RequestParam String name
	) {
		
		if ((StringUtils.hasText(name)) && check_str(name)) {
		
			mems.setName(name);
			mems.destroy();
			mems.setCount(0);
			
			return "redirect:/user";
		}
		
		model.addAttribute("message", "Name should contain only letters 'A'..'Z' or 'a'..'z'");
		return "main.html";
	}
	
	@GetMapping("/user")
	public String userPage(Model model) {
		model.addAttribute("persons_name", mems.getName());
		
		model.addAttribute("mems", mems.findAccountsbyName(mems.getName()));
		
		return "user.html";
	}
	
	@GetMapping("/image")
	public String showImage(
			@RequestParam String meme_name,
			@RequestParam(required = false) String top,
			@RequestParam(required = false) String bottom,
			Model model
	) {
		try {
			
			String url = "https://api.dicebear.com/9.x/";
			
			url += meme_name;
			url += "/svg";
			
			
			ResponseEntity<String> response = rest.getForEntity(url, String.class);
			String dataUrl = response.getBody();
			model.addAttribute("imageSrc", dataUrl);
			
			return "image.html";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/user_choice")
	public String returnPage(
			@RequestParam(required = false) String sign,
			Model model
	) {
		
		if (sign.equals("error")) {
			
			model.addAttribute("persons_name", mems.getName());
			model.addAttribute("mems", mems.findAccountsbyName(mems.getName()));
			model.addAttribute("message", "Text should contain only letters 'A'..'Z' or 'a'..'z'");
			
			return "user.html";
		}
		
		if (Meme_name != null && sign != null) {
			Mem m = new Mem();
			m.first_name = mems.getName();
			m.mem_name = Meme_name;
			if (sign.equals("like")) {
				m.like_mem = "YES";
			} else {
				m.like_mem = "NO";
			}
			
			
			if (mems.capacity() < mems.getCount()) {
				mems.addMem(m);
				mems.addPerson(mems.getName(), Meme_name, m.like_mem);
			}

			model.addAttribute("persons_name", mems.getName());
			model.addAttribute("mems", mems.findAccountsbyName(mems.getName()));
		}
		
		return "user.html";
	}
	
	
	@PostMapping("/user_choice")
	public String sendData(
			@RequestParam String meme,
			@RequestParam(required = false) String top,
			@RequestParam(required = false) String bottom,
			Model model
	) {
		
		String url_meme_address = "redirect:/image?meme_name=";
			
		Meme_name = meme;
		url_meme_address += meme;
		
		url_meme_address += "&top=";
		if (StringUtils.hasText(top)) {
			if (!check_str(top)) {
				return "redirect:/user_choice?sign=error";
			} else {
				url_meme_address += top;
			}
		}
			
		url_meme_address += "&bottom=";
		if (StringUtils.hasText(bottom)) {
			if (!check_str(bottom)) {
				return "redirect:/user_choice?sign=error";
			} else {
				url_meme_address += bottom;
			}
		}
			
		mems.addCount();
			
		return url_meme_address;
	}
	
}
