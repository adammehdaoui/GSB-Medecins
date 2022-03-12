package io.github.mehdaouiadam.devap4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String Homepage() {
        return "Homepage";
    }

    @GetMapping("/dev")
    public String Dev() { return "Dev"; }

}
