package org.petdians.user.pet.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/pet")
public class PetController {

    @GetMapping("/list")
    public void getList(){

    }

    @GetMapping("/read")
    public void getRead(){

    }
}
