package com.uniovi.sdi2324713spring.controllers;

import com.uniovi.sdi2324713spring.entities.Mark;
import com.uniovi.sdi2324713spring.entities.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.uniovi.sdi2324713spring.services.ProfessorsService;

@RestController
public class ProfessorController {

    @Autowired //Inyectar el servicio
    private ProfessorsService professorService;

    @RequestMapping("/professor/list")
    public String getList() {
        return professorService.getProfessors().toString();
    }

    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String setProfessor(@ModelAttribute Professor professor) {
        professorService.addProfessor(professor);
        return "OK";
    }

    @RequestMapping("/professor/add")
    public String getProfessor() {
        return professorService.getProfessors().toString();
    }

    @RequestMapping("/professor/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        return professorService.getProfessor(id).toString();
    }

    @RequestMapping("/professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return "Ok";
    }

    @RequestMapping(value="/professor/edit/{id}", method=RequestMethod.POST)
    public String setEdit(@ModelAttribute Professor professor, @PathVariable Long id){
        professor.setId(id);
        professorService.addProfessor(professor);
        return "OK";
    }

    @RequestMapping(value = "/professor/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        return professorService.getProfessor(id).toString();
    }

}