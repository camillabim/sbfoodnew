package br.com.softblue.bluefood.infrastructure.web.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.softblue.bluefood.application.ClienteService;
import br.com.softblue.bluefood.application.RestauranteService;
import br.com.softblue.bluefood.application.ValidationException;
import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;

//N�o gerenciada (detached)
@Controller
@RequestMapping(path = "/public")
public class PublicController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@GetMapping("/cliente/new") // ent�o ficaria: public/cliente/new
	public String newCliente(Model model) {//objeto model � um objeto que n�o interessa da onde veio, o que importa que � vira um objeto - comunica��o com thymeleaf
		model.addAttribute("cliente", new Cliente());
		ControllerHelper.setEditMode(model, false);
		
		return "cliente-cadastro";
	}
	
	@GetMapping("/restaurante/new") // ent�o ficaria: public/cliente/new
	public String newRestaurante(Model model) {//objeto model � um objeto que n�o interessa da onde veio, o que importa que � vira um objeto - comunica��o com thymeleaf
		model.addAttribute("restaurante", new Restaurante());
		
		ControllerHelper.setEditMode(model, false);
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);		
		return "restaurante-cadastro";
	}
	
	@PostMapping(path = "/cliente/save")
	public String saveCliente(@ModelAttribute("cliente") @Valid Cliente cliente, 
						      Errors errors,
						      Model model) {
		
		if(!errors.hasErrors()) {
			try {
				clienteService.saveCliente(cliente);	
				model.addAttribute("msg", "Cliente gravado com sucesso!");	
			}catch(ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());
			}	
		}
		
		ControllerHelper.setEditMode(model, false);
		return "cliente-cadastro";
	}
	
	@PostMapping(path = "/restaurante/save")
	public String saveRestaurante(@ModelAttribute("restaurante") @Valid Restaurante restaurante, 
						      Errors errors,
						      Model model) {
		
		if(!errors.hasErrors()) {
			try {
				restauranteService.saveRestaurante(restaurante);	
				model.addAttribute("msg", "Restaurante gravado com sucesso!");	
			}catch(ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());
			}	
		}
		
		ControllerHelper.setEditMode(model, false);
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		return "restaurante-cadastro";
	}
}