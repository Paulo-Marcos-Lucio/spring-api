package com.tilog.logtiapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tilog.logtiapi.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
