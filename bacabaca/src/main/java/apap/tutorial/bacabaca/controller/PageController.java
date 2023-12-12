package apap.tutorial.bacabaca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @Autowired
    ServerProperties serverProperties;

    @RequestMapping("port")
    public String ActivePort(Model model) {

        model.addAttribute("port", serverProperties.getPort());

        return "active-port";
    }
    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
}
