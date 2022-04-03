package com.tilog.logtiapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tilog.logtiapi.exceptions.EntidadeNaoEncontradaException;
import com.tilog.logtiapi.model.Cliente;
import com.tilog.logtiapi.model.Endereco;
import com.tilog.logtiapi.repositories.ClienteRepository;
import com.tilog.logtiapi.repositories.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Cliente insert(Cliente c) {
		Long id = c.getEndereco().getId();
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		
		if(endereco.get() == null) {
			throw new EntidadeNaoEncontradaException("Id de endereço nao existente!");
		}
		
		c.setEndereco(endereco.get());
		return clienteRepository.save(c);
	}
	
	public void deleteById(Long id) {
		Cliente c = clienteRepository.getById(id);
		if(c == null) {
			throw new EntidadeNaoEncontradaException("Id de cliente nao encontrado.");
		}
		
		clienteRepository.deleteById(id);
	}
}
