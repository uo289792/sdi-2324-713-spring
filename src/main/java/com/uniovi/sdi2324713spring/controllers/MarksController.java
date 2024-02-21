package com.uniovi.sdi2324713spring.controllers;

import com.uniovi.sdi2324713spring.services.UsersService;
import com.uniovi.sdi2324713spring.validators.MarksValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.uniovi.sdi2324713spring.entities.Mark;
import com.uniovi.sdi2324713spring.services.MarksService;
import java.util.Set;
import java.util.HashSet;
import javax.servlet.http.HttpSession;


@Controller
public class MarksController {

    //Inyectar el servicio
    private final MarksService marksService;
    private final UsersService usersService;

    private final MarksValidator marksValidator;
    private final HttpSession httpSession;

    // Inyectamos el servicio por inyección basada en constructor
    public MarksController(MarksService marksService, UsersService usersService, MarksValidator marksValidator, HttpSession httpSession) {
        this.marksService = marksService;
        this.usersService = usersService;
        this.marksValidator = marksValidator;
        this.httpSession = httpSession;
    }

    @RequestMapping("/mark/list")
    public String getList(Model model) {
        model.addAttribute("markList", marksService.getMarks());
        return "mark/list";
    }

    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(@Validated Mark mark, BindingResult result) {
        marksValidator.validate(mark, result);
        if(result.hasErrors()) {
            return "/mark/add";
        }
        marksService.addMark(mark);
        return "redirect:/mark/list";
    }

    @RequestMapping(value="/mark/add")
    public String getMark(Model model){
        model.addAttribute("usersList", usersService.getUsers());
        model.addAttribute("mark", new Mark());
        return "mark/add";
    }

    @RequestMapping("/mark/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        return "mark/details";
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        marksService.deleteMark(id);
        return "redirect:/mark/list";
    }

    @RequestMapping(value="/mark/edit/{id}", method=RequestMethod.POST)
    public String setEdit(@ModelAttribute Mark mark, @PathVariable Long id){
        Mark originalMark = marksService.getMark(id);
        // modificar solo score y description
        originalMark.setScore(mark.getScore());
        originalMark.setDescription(mark.getDescription());
        marksService.addMark(originalMark);
        return "redirect:/mark/details/"+id;
    }

    @RequestMapping(value = "/mark/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/edit";
    }

    @RequestMapping("/mark/list/update")
    public String updateList(Model model){
        model.addAttribute("markList", marksService.getMarks() );
        return "mark/list :: marksTable";
    }

}



