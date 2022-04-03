package com.tilog.logtiapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tilog.logtiapi.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:nome%")
	public List<Cliente> retornaPorLetra(@Param("nome") String nome);
	
	@Query("SELECT c FROM Cliente c WHERE c.nome = :nome")
	public List<Cliente> retornaPorNome(@Param("nome") String nome);
	
	@Query("from Cliente where nome like %:nome% and endereco.id = :id")
	public List<Cliente> retornaPorNomeIdProduto(String nome, @Param("id") Long id);
	
	@Query("from Cliente where saldo between :valorInicial and :valorFinal")
	public List<Cliente> retornaPorSaldo(Double valorInicial, Double valorFinal);
	
	

}
