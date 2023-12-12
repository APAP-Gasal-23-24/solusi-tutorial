package apap.tutorial.pacil.controller;

import apap.tutorial.pacil.dto.LoginRequestDTO;
import apap.tutorial.pacil.dto.TokenDTO;
import apap.tutorial.pacil.restservice.UserRestService;
import apap.tutorial.pacil.security.xml.Attributes;
import apap.tutorial.pacil.security.xml.ServiceResponse;
import apap.tutorial.pacil.setting.Setting;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Controller
public class PageController {

        @Autowired
        private UserRestService userRestService;

    private WebClient webClient = WebClient.builder().codecs(configurer -> configurer.defaultCodecs()
                    .jaxb2Decoder(new Jaxb2XmlDecoder()))
            .build();

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
            @RequestParam(value = "ticket", required = false) String ticket,
            HttpServletRequest request
    ) {

        System.out.println("ticket1: " + ticket);
        System.out.println("request1: " + request);
        ServiceResponse serviceResponse = this.webClient.get().uri(
            String.format(
                    Setting.SERVER_VALIDATE_TICKET,
                    ticket,
                    Setting.CLIENT_LOGIN
            )
            ).retrieve().bodyToMono(ServiceResponse.class).block();

        Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();
//
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "pacilia", null);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
//
        String name = attributes.getNama();
        var token = userRestService.getToken(username, name);

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext); 
        httpSession.setAttribute("token", token);
        System.out.println("Authentication successful: " + authentication.getName());

        System.out.println(token);
        return new ModelAndView("redirect:/user/add");
    }

    @GetMapping("/login-sso")
    public ModelAndView loginSSO() {
        return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

    @GetMapping("/logout-sso")
    public ModelAndView logoutSSO(Principal principal) {
        return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }
}
