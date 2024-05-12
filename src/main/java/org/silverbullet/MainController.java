package org.silverbullet;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String index(Model model, Principal principal) {
		return "forward:/index.html";
//		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model, Principal principal) {
		return "index";
	}
	
	@GetMapping("/test")
	public String test(Model model, Principal principal) {
		return "index";
	}
}
