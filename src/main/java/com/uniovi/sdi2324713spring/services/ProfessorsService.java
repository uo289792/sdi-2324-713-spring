package com.uniovi.sdi2324713spring.services;

import com.uniovi.sdi2324713spring.entities.User;
import com.uniovi.sdi2324713spring.repositories.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniovi.sdi2324713spring.entities.Professor;

import java.util.*;


@Service
public class ProfessorsService {

    private final ProfessorsRepository professorsRepository;

    public ProfessorsService(ProfessorsRepository professorsRepository) {
        this.professorsRepository = professorsRepository;
    }

    public List<Professor> getProfessors() {
        List<Professor> professors = new ArrayList<Professor>();
        professorsRepository.findAll().forEach(professors::add);
        return professors;
    }
    public Professor getProfessor(Long id) {
        return professorsRepository.findById(id).get();
    }
    public void addProfessor(Professor professor) {
        // Si en Id es null le asignamos el último + 1 de la lista
        professorsRepository.save(professor);
    }
    public void deleteProfessor(Long id) {
        professorsRepository.deleteById(id);
    }

    public Professor getProfessorByDni(String dni) {
        return professorsRepository.findByDni(dni);
    }

}
