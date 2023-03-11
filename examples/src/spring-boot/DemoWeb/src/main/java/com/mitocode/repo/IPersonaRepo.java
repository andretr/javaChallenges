package com.mitocode.repo;

import com.mitocode.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IPersonaRepo extends JpaRepository<Persona, Integer>{
	
}
