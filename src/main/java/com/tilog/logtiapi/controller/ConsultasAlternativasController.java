package com.tilog.logtiapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tilog.logtiapi.model.Cliente;
import com.tilog.logtiapi.repositories.ClienteRepository;

@RequestMapping("/teste")
@RestController
public class ConsultasAlternativasController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping("/retorna-nome-por-letra")
	@GetMapping
	public ResponseEntity<List<Cliente>> consultaNomePorLetra(String nome) {
		List<Cliente> clientess = clienteRepository.retornaPorLetra(nome);
		return ResponseEntity.status(HttpStatus.OK).body(clientess);
	}
	
	@RequestMapping("/retorna-por-nome")
	@GetMapping
	public ResponseEntity<List<Cliente>> consultaPorNome(String nome) {
		List<Cliente> clientes2 = clienteRepository.retornaPorNome(nome);
		return ResponseEntity.status(HttpStatus.OK).body(clientes2);
	}
	
	@RequestMapping("/retorna-por-nome-id-endereco")
	@GetMapping
	public ResponseEntity<List<Cliente>> consultaPorNomeIdProduto(String nome, Long id) {
		List<Cliente> clientes3 = clienteRepository.retornaPorNomeIdProduto(nome, id);
		return ResponseEntity.status(HttpStatus.OK).body(clientes3);
	}
	
	@RequestMapping("/retorna-por-saldo")
	@GetMapping
	public ResponseEntity<List<Cliente>> consultaPorSaldo(Double valorInicial, Double valorFinal) {
		List<Cliente> clientes4 = clienteRepository.retornaPorSaldo(valorInicial, valorFinal);
		return ResponseEntity.status(HttpStatus.OK).body(clientes4);
	}
	
}
