package br.com.softblue.bluefood;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {

	private String nome;
	
	public static void main(String[] args) {
		Cliente c = new Cliente();
		c.setNome("Camilla Bim");
		System.out.println(c.nome);
	}
	
}