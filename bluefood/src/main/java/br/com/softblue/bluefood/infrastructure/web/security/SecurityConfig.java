package br.com.softblue.bluefood.infrastructure.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration //para o spring saber que � uma classe para configura��o de alguma coisa
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { //definir como vai funcionar o processo de autentica��o
		http.csrf().disable()//desabilita recurso
			.authorizeRequests()//autorizo a..:
			.antMatchers("/images/**","/css/**","/js/**","/public","/sbpay").permitAll()//todos esses padr�es � permitido
			.antMatchers("/cliente/**").hasRole(Role.CLIENTE.toString())
			.antMatchers("/restaurante/**").hasRole(Role.RESTAURANTE.toString())
			.anyRequest().authenticated()//qualquer coisa relacionado acima precisa ter autentica��o
			.and()
			.formLogin()//vou usar um formulario para fazer autentica��o
				.loginPage("/login")//utiliza esse padr�o para autenticar
				.failureUrl("/login-error") //ir para a pagina de erro de login
				//.successHandler(null) // para ser chamado quando n der certo
				.permitAll() 
			.and()
				.logout()
				.logoutUrl("/logout")
				.permitAll();
		
		
	}

}
