package br.com.softblue.bluefood.infrastructure.web.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.usuario.Usuario;

@SuppressWarnings("serial")
public class LoggedUser implements UserDetails{

	private Usuario usuario;
	
	private Role role;
	
	private Collection<? extends GrantedAuthority> roles;
	
	
	public LoggedUser(Usuario usuario) {
		this.usuario = usuario;	

		Role role;
		
		if(usuario instanceof Cliente) {
			role = Role.CLIENTE;
		}else if(usuario instanceof Restaurante) {
			role = Role.RESTAURANTE;
		}else {
			throw new IllegalStateException("O tipo de usuário não é válido");
		}
		
		this.role = role;
		this.roles = List.of(new SimpleGrantedAuthority("ROLE" + role));
		
			
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //pegar os roles
		return roles;
	}

	@Override
	public String getPassword() { //pegar a senha
		return usuario.getSenha();
	}

	@Override
	public String getUsername() { //pegar o usuário
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() { //a conta expirou?
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // a conta m esta bloqueada
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // as credencias nao estaao expiradas
		return true;
	}

	@Override
	public boolean isEnabled() { // a conta está habilitada?
		return true;
	}

	public Role getRole() {
		return role;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
}
