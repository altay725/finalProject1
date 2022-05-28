package com.example.demo.controller;

import com.example.demo.model.Computer;
import com.example.demo.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/computer-base")
@AllArgsConstructor

public class BaseController {
    @Autowired
    private final MainService mainService;

    @GetMapping
    public String getPage(Model model){
        model.addAttribute("computer_list",mainService.getMyComputerList());
        return "base";
    }

    @GetMapping("/add-computer")
    public String getAddComputerPage(Model model){
        model.addAttribute("computer", new Computer());
        return "add-product";
    }

    @PostMapping("/save")
    public String saveComputer(
            @Valid @ModelAttribute("computer") Computer theComputer,
            Errors errors,
            @RequestParam("computer_image") MultipartFile multipartFile

    ){
        if(errors.hasErrors()){
            return "add-product";
        }
        System.out.println(multipartFile);
        mainService.addComputer(theComputer,multipartFile);
        return "redirect:/computer-base";
    }

    @GetMapping("/delete/{id}")
    public String deleteComputerById(
            @PathVariable("id") long id
    ){
        mainService.deleteComputerById(id);
        return "redirect:/computer-base";
    }

    @GetMapping("/edit/{id}")
    public String editComputer(
            @PathVariable("id") long id,
            Model model
    ){
        Computer computer = mainService.getComputerById(id);
        model.addAttribute("computer",computer);
        return "add-product";
    }

}