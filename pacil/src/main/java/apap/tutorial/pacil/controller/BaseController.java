package apap.tutorial.pacil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String home2() {
        return "home2";
    }
}
