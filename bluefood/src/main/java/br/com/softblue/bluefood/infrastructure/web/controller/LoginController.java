package br.com.softblue.bluefood.infrastructure.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	//método para abrir a página de login
	@GetMapping(path = { "/login", "/" })
	public String login() {
		return "login"; // retorna o nome da página sem a extensão
	}
	
}
