package br.com.softblue.bluefood.infrastructure.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import br.com.softblue.bluefood.util.SecurityUtils;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
 
		Role role = SecurityUtils.loggedUser().getRole(); // pega o perfil de acesso do usuário logado
		if (role == Role.CLIENTE) { 
			response.sendRedirect("/cliente/home"); // direciona para a pagina do cliente
		}else if(role == Role.RESTAURANTE) {
			response.sendRedirect("/restaurante/home");
		}else {
			throw new IllegalStateException("Erro na autenticação");
		}
		
	}

	
	
}
