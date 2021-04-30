package org.petdians.user.animal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.petdians.user.animal.dto.MissingAnimalDTO;
import org.petdians.user.animal.service.AnimalService;
import org.petdians.user.common.dto.PageRequestDTO;
import org.petdians.user.common.dto.PageResultDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        log.info("animalRegister Get.......................");

        model.addAttribute("result", service.getList(pageRequestDTO));

    }

    @PostMapping("/register")
    public String postAnimalRegister(MissingAnimalDTO missingAnimalDTO, RedirectAttributes redirectAttributes) {

        log.info("animalRegister Post.......................");

        //animalCode = name + sex + species + age
        missingAnimalDTO.setAnimalCode(missingAnimalDTO.getName() + missingAnimalDTO.getSex() + missingAnimalDTO.getSpecies() + missingAnimalDTO.getAge());

        log.info(missingAnimalDTO);

        Integer animalNumber = service.register(missingAnimalDTO);

        redirectAttributes.addFlashAttribute("animalNumber", animalNumber);

        return "redirect:/animal/read";

    }

    @GetMapping({"/read","/modify"})
    public void getAnimalRead(PageRequestDTO pageRequestDTO, Model model) {

        log.info("animalRead Get.......................");

        model.addAttribute("result", service.getList(pageRequestDTO));

    }

}
