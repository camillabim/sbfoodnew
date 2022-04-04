package br.com.softblue.bluefood.domain.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.softblue.bluefood.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass // dizer para a jpa que apesar dessa classe nao ser uma entidade, essa classe faz parte de uma outra entidade que foi criada (que serao cliente e restaurante)
public class Usuario implements Serializable{//implementa serializable para mostrar para a jpa que a classe pode ser serializavel, ou seja transformada em bytes

	@EqualsAndHashCode.Include
	@Id// define que este � o id da classe
	@GeneratedValue(strategy = GenerationType.IDENTITY) //gera o id automaticamente pelo db -- identity que � incrementado de um em um (o db precisa suportar isso)
	private Integer id;
	
	@NotBlank(message = "O nome n�o pode ser vazio")
	@Size(max = 80, message = "O nome � muito grande")
	private String nome;
	
	@NotBlank(message = "O e-mail n�o pode ser vazio")
	@Size(max = 60, message = "O e-mail � muito grande")
	@Email(message = "O e-mail � inv�lido")
	private String email;
	
	@NotBlank(message = "A senha n�o pode ser vazia")
	@Size(max = 80, message = "A senha � muito grande")
	private String senha;
	
	@NotBlank(message = "O telefone n�o pode ser vazio")
	@Pattern(regexp = "[0-9]{11}", message = "O telefone possui formato inv�lido")
	@Column(length = 11, nullable = false)
	private String telefone;
	
	
	public void encryptPassword() {
		this.senha = StringUtils.encrypt(this.senha);
	}
}
