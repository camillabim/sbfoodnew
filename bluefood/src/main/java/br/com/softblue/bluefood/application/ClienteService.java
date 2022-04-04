package br.com.softblue.bluefood.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;

//classe respons�vel por fazer valida��es pertinentes ao cliente
//Gerenciada (managed)
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Transactional //est� sendo executado em um contexto de uma 'transa��o' no banco de dados isso garante que seja rodado tudo em uma �nica transa��o evitando inconsist�ncia
	public void saveCliente(Cliente cliente) throws ValidationException{

		if(!validateEmail(cliente.getEmail(), cliente.getId())){
			throw new ValidationException("O e-mail est� duplicado");
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
