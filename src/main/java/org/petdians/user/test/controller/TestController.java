package org.petdians.user.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
@Log4j2
@RequiredArgsConstructor
public class TestController {

    @GetMapping({"/testFCM"})
    public void getTestFCM() {

        log.info("testFCM............................");

    }

    @GetMapping({"/testSendFCM"})
    public void getTestSendFCM() {

        log.info("testSendFCM............................");

    }

}
