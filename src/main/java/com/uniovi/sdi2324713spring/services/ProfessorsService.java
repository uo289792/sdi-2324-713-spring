package com.uniovi.sdi2324713spring.services;

import org.springframework.stereotype.Service;
import com.uniovi.sdi2324713spring.entities.Professor;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ProfessorsService {
    private List<Professor> professorsList = new LinkedList<>();
    @PostConstruct
    public void init() {
        professorsList.add(new Professor(1L, "1", "David", "Muñoz", "C1"));
        professorsList.add(new Professor(2L, "2", "Lionel", "Messi", "C2"));
    }
    public List<Professor> getProfessors() {
        return professorsList;
    }
    public Professor getProfessor(Long id) {
        return professorsList.stream()
                .filter(mark -> mark.getId().equals(id)).findFirst().get();
    }
    public void addProfessor(Professor professor) {
        // Si en Id es null le asignamos el ultimo + 1 de la lista
        if (professor.getId() == null) {
            professor.setId(professorsList.get(professorsList.size() - 1).getId() + 1);
        }
        professorsList.add(professor);
    }
    public void deleteProfessor(Long id) {
        professorsList.removeIf(mark -> mark.getId().equals(id));
    }
}


