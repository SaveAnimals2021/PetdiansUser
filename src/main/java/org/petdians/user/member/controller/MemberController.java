package org.petdians.user.member.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public void getLogin(){
        log.info("============ GET LOGIN ============");
    }

    @GetMapping("/newList")
    public void getNewList(){
        log.info("============ GET New List ============");
    }

}