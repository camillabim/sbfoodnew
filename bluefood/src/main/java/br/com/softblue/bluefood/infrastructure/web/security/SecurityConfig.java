package br.com.softblue.bluefood.infrastructure.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration //para o spring saber que é uma classe para configuração de alguma coisa
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new AuthenticationSuccessHandlerImpl();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { //definir como vai funcionar o processo de autenticação
		http.csrf().disable()//desabilita recurso
			.authorizeRequests()//autorizo a..:
			.antMatchers("/images/**","/css/**","/js/**","/public","/sbpay").permitAll()//todos esses padrões é permitido
			.antMatchers("/cliente/**").hasRole(Role.CLIENTE.toString())
			.antMatchers("/restaurante/**").hasRole(Role.RESTAURANTE.toString())
			.anyRequest().authenticated()//qualquer coisa relacionado acima precisa ter autenticação
			.and()
			.formLogin()//vou usar um formulario para fazer autenticação
				.loginPage("/login")//utiliza esse padrão para autenticar
				.failureUrl("/login-error") //ir para a pagina de erro de login
				.successHandler(authenticationSuccessHandler()) // para ser chamado quando der certo
				.permitAll() 
			.and()
				.logout()
				.logoutUrl("/logout")
				.permitAll();
		
		
	}

}
