package com.tilog.logtiapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tilog.logtiapi.exceptions.EntidadeNaoEncontradaException;
import com.tilog.logtiapi.model.Cliente;
import com.tilog.logtiapi.repositories.ClienteRepository;
import com.tilog.logtiapi.services.ClienteService;

@RequestMapping("/clientes")
@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}

	@GetMapping
	@RequestMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Cliente> insert(@RequestBody Cliente c) {
		try {
			clienteService.insert(c);
			return ResponseEntity.status(HttpStatus.CREATED).body(c);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		try {
			Optional<Cliente> clienteAtual = clienteRepository.findById(id);

			if (clienteAtual.isPresent()) {
				BeanUtils.copyProperties(cliente, clienteAtual.get(), "id");
				Cliente clienteSalvo = clienteService.insert(clienteAtual.get());
				return ResponseEntity.status(HttpStatus.OK).body(clienteSalvo);
			}

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		try {
			clienteService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
}
