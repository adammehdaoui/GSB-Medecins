package io.github.mehdaouiadam.devap4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String Homepage() {
        return "Homepage";
    }

    @GetMapping(path = "/login")
    public String login(@RequestParam(name = "error", required = false) String error, Model model) {
        // Set the "error" attribute to true if the request parameter "error" is present
        if (error != null) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }

        model.addAttribute("title", "Connexion");
        return "login";
    }

    @GetMapping("/dev")
    public String Dev() { return "Dev"; }
}
