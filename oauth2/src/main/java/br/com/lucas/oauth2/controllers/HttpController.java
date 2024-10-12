package br.com.lucas.oauth2.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {

    @GetMapping("/public")
    public String publicRoute(){
        return "<h1>Welcome to the Public Zone!</h1>";
    }

    @GetMapping("/private")
    public String privateRoute(@AuthenticationPrincipal OidcUser principal){
        return "<h1>Welcome to the Private Zone!</h1>";
    }

}
