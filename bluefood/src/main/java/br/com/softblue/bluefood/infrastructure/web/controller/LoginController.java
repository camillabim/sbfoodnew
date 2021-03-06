package br.com.softblue.bluefood.infrastructure.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	//m?todo para abrir a p?gina de login
	@GetMapping(path = { "/login", "/" })
	public String login() {
		return "login"; // retorna o nome da p?gina sem a extens?o
	}
	
	@GetMapping(path = "/login-error")
	public String loginError(Model model) {
		model.addAttribute("msg","Credenciais inv?lidas");
		return "login";
		
	}
}
