package org.petdians.user.animal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.petdians.user.animal.service.AnimalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/animal")
@Log4j2
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService service;

    @GetMapping("/list")
    public void getAnimalList(Model model) {

        Pageable pageable = PageRequest.of(0,10, Sort.by("animalnumber").descending());

        log.info("animalList.......................");

        model.addAttribute("list", service.getAnimalList(pageable));

    }

}
