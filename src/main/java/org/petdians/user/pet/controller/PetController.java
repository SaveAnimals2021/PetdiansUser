package org.petdians.user.pet.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.petdians.user.member.entity.Member;
import org.petdians.user.pet.dto.PetDTO;
import org.petdians.user.pet.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping("/list")
    public void getList(Model model){
        List<PetDTO> petDTOList = petService.getPetsByMemberID("mk");

        model.addAttribute("list", petDTOList);
    }

    @GetMapping("/read")
    public void getRead(){

    }

    @GetMapping("/register")
    public void getRegister(){

    }

    @PostMapping("/register")
    public String postRegister(PetDTO petDTO, RedirectAttributes redirectAttributes){

        log.info("petDTO: " + petDTO);
        petDTO.setMember(Member.builder().memberID("mk").build());
        Long pno = petService.register(petDTO);

        // redirectAttributes.addFlashAttribute("msg", mno);

        // return "redirect:/movie/list";
        return "";

    }

    @GetMapping("/registerTest")
    public void getRegisterTest(){

    }
}
