package org.petdians.user.common.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    @GetMapping({"/", "/main"})
    public String getMain(){
        return "/main";
    }

    @GetMapping( "/testview")
    public void getTestview(){
    }
}
