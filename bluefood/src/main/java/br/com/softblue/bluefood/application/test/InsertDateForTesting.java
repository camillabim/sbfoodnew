package br.com.softblue.bluefood.application.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestaurante;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.ItemCardapio;
import br.com.softblue.bluefood.domain.restaurante.ItemCardapioRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;
import br.com.softblue.bluefood.util.StringUtils;

@Component // para o spring saber que precisa gerenciar essa classe
public class InsertDateForTesting {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@Autowired
	private ItemCardapioRepository itemCardapioRepository;
	
	@EventListener // m?todo para ser invocado no momento em que a aplica??o termina de iniciar
	public void onApplicationEvent(ContextRefreshedEvent event) {
		clientes();
		Restaurante[] restaurantes = restaurantes();
		itensCardapio(restaurantes);
	}
	
	private Restaurante[] restaurantes() {
		List<Restaurante> restaurantes = new ArrayList<>();
		
		CategoriaRestaurante categoriaPizza = categoriaRestauranteRepository.findById(1).orElseThrow();
		CategoriaRestaurante categoriaSanduiche = categoriaRestauranteRepository.findById(2).orElseThrow();
		CategoriaRestaurante categoriaSobremesa = categoriaRestauranteRepository.findById(5).orElseThrow();
		CategoriaRestaurante categoriaJapones = categoriaRestauranteRepository.findById(6).orElseThrow();
		
		Restaurante r = new Restaurante();
		r.setNome("Bubger King");
		r.setEmail("restaurante@gmail.com");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("18211168000101");
		r.setTaxaEntrega(BigDecimal.valueOf(3.2));
		r.setTelefone("11946685544");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0001-logo.png");
		r.setTempoEntregaBase(30);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Mc Naldo's");
		r.setEmail("restaurante2@gmail.com");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("18211168000102");
		r.setTaxaEntrega(BigDecimal.valueOf(4.5));
		r.setTelefone("11946685545");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0002-logo.png");
		r.setTempoEntregaBase(25);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Sbubby");
		r.setEmail("restaurante3@gmail.com");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("18211168000103");
		r.setTaxaEntrega(BigDecimal.valueOf(12.2));
		r.setTelefone("11946685547");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0003-logo.png");
		r.setTempoEntregaBase(38);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Pizza Brut");
		r.setEmail("restaurante4@gmail.com");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("18211168000104");
		r.setTaxaEntrega(BigDecimal.valueOf(9.8));
		r.setTelefone("11946685541");
		r.getCategorias().add(categoriaPizza);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0004-logo.png");
		r.setTempoEntregaBase(22);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		
		
		r = new Restaurante();
		r.setNome("Wiki Japa");
		r.setEmail("restaurante5@gmail.com");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("18211168000104");
		r.setTaxaEntrega(BigDecimal.valueOf(14.9));
		r.setTelefone("11946685540");
		r.getCategorias().add(categoriaJapones);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0005-logo.png");
		r.setTempoEntregaBase(19);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		Restaurante[] array = new Restaurante[restaurantes.size()];
		return restaurantes.toArray(array);
	}
	
	private Cliente[] clientes() {
		List<Cliente> clientes = new ArrayList<>();
		
		Cliente c = new Cliente();
		c.setNome("Camilla Bim");
		c.setEmail("camillabim@hotmail.com.br");
		c.setSenha(StringUtils.encrypt("c"));
		c.setCep("03073005");
		c.setCpf("39760326892");
		c.setTelefone("11946685548");
		clienteRepository.save(c);
		clientes.add(c);
		
		c = new Cliente();
		c.setNome("Tunico Bim");
		c.setEmail("tutu@hotmail.com.br");
		c.setSenha(StringUtils.encrypt("c"));
		c.setCep("03073003");
		c.setCpf("39760326895");
		c.setTelefone("11946685544");
		clienteRepository.save(c);
		clientes.add(c);
		
		Cliente[] array = new Cliente[clientes.size()];
		return clientes.toArray(array);
		
	}
	
	private void itensCardapio(Restaurante[] restaurantes) {
		ItemCardapio ic = new ItemCardapio();
		ic.setCategoria("Sandu?che");
		ic.setDescricao("Delicioso sandu?che com molho");
		ic.setNome("Double Cheese Burguer Special");
		ic.setPreco(BigDecimal.valueOf(23.80));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(true);
		ic.setImagem("0001-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Sandu?che");
		ic.setDescricao("Sandu?che natural com peito de peru");
		ic.setNome("Sandu?che Natural da Casa");
		ic.setPreco(BigDecimal.valueOf(11.20));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(false);
		ic.setImagem("0007-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Bebida");
		ic.setDescricao("Refrigerante com g?s");
		ic.setNome("Refrigerante tradicional");
		ic.setPreco(BigDecimal.valueOf(9));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(false);
		ic.setImagem("0004-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Bebida");
		ic.setDescricao("Suco Natural e docinho");
		ic.setNome("Suco de laranja");
		ic.setPreco(BigDecimal.valueOf(9));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(false);
		ic.setImagem("0005-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Pizza");
		ic.setDescricao("Pizza deliciosa");
		ic.setNome("Pizza de mu?arela");
		ic.setPreco(BigDecimal.valueOf(38.90));
		ic.setRestaurante(restaurantes[3]);
		ic.setDestaque(false);
		ic.setImagem("0002-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Japonesa");
		ic.setDescricao("Delicioso hot holl");
		ic.setNome("Hot holl");
		ic.setPreco(BigDecimal.valueOf(16.80));
		ic.setRestaurante(restaurantes[4]);
		ic.setDestaque(false);
		ic.setImagem("0003-comida.png");
		itemCardapioRepository.save(ic);
	}
	
	
	
	
}
