package com.tec.os.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tec.os.model.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

	@Query("SELECT a FROM Procedimento a WHERE a.descricao LIKE %:descricao%")
	List<Procedimento> findByDescricao(@Param("descricao")String descricao);

	
	
	
		
}
