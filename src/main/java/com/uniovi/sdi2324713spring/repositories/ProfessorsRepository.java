package com.uniovi.sdi2324713spring.repositories;

import com.uniovi.sdi2324713spring.entities.Professor;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorsRepository extends CrudRepository<Professor, Long> {
}
