package br.com.softblue.bluefood.infrastructure.web.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import br.com.softblue.bluefood.domain.restaurante.CategoriaRestaurante;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;

//Helper é um designer patter - classe auxiliadora
public class ControllerHelper {

	public static void setEditMode(Model model, boolean isEdit) {
		model.addAttribute("editMode", isEdit);
	}
	
	public static void addCategoriasToRequest(CategoriaRestauranteRepository repository, Model model) {
		//retorna tudo que está cadastrado na tabela
		List<CategoriaRestaurante> categorias = repository.findAll(Sort.by("nome")); //ordenado por nome
		model.addAttribute("categorias", categorias);
				
	}
}
