package org.petdians.user.animal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.petdians.user.animal.service.AnimalService;
import org.petdians.user.common.dto.PageRequestDTO;
import org.petdians.user.common.dto.PageResultDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/animal")
@Log4j2
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService service;

    @GetMapping("/list")
    public void getAnimalList(PageRequestDTO pageRequestDTO, Model model) {

        log.info("animalList.......................");

        PageResultDTO result = service.getList(pageRequestDTO);
        //result.getDtoList().forEach(o -> log.info(o));

        //result.getPageList().forEach(o -> log.info(o));

        model.addAttribute("result", service.getList(pageRequestDTO));

    }

    @GetMapping("/register")
    public void getAnimalRegister(PageRequestDTO pageRequestDTO, Model model) {

        log.info("animalList.......................");

        PageResultDTO result = service.getList(pageRequestDTO);
        result.getDtoList().forEach(o -> log.info(o));

        model.addAttribute("result", service.getList(pageRequestDTO));

    }

    @PostMapping("/register")
    public void postAnimalRegister(PageRequestDTO pageRequestDTO, Model model) {

        log.info("animalList.......................");

        PageResultDTO result = service.getList(pageRequestDTO);
        result.getDtoList().forEach(o -> log.info(o));

        model.addAttribute("result", service.getList(pageRequestDTO));

    }

}
