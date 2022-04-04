package br.com.softblue.bluefood.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;

//classe responsável por fazer validações pertinentes ao cliente
//Gerenciada (managed)
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Transactional //está sendo executado em um contexto de uma 'transação' no banco de dados isso garante que seja rodado tudo em uma única transação evitando inconsistência
	public void saveCliente(Cliente cliente) throws ValidationException{

		if(!validateEmail(cliente.getEmail(), cliente.getId())){
			throw new ValidationException("O e-mail está duplicado");
		}
		
		if(cliente.getId() != null) {//alterando cliente
			Cliente clienteDB = clienteRepository.findById(cliente.getId()).orElseThrow();
			cliente.setSenha(clienteDB.getSenha());
			
		}else { //criando um novo cliente
			cliente.encryptPassword();
		}
		
		clienteRepository.save(cliente);
		
	}
	
	private boolean validateEmail(String email, Integer id) {
		Cliente cliente = clienteRepository.findByEmail(email);
		Restaurante restaurante = restauranteRepository.findByEmail(email);
		
		if(restaurante != null) {
			return false;
		}
		
		if(cliente != null) {
			if(id == null) {
				return false;
			}
			
			if(!cliente.getId().equals(id)) {
				return false;
			}
		}
		
		return  true;
	}
	
}
