package com.tec.os.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tec.os.model.Guia;

public interface GuiaRepository extends JpaRepository<Guia, Long> {

	@Query("SELECT a FROM Guia a WHERE a.nome LIKE %:nome%")
	List<Guia> findByNome(@Param("nome")String nome);

	
		
}
