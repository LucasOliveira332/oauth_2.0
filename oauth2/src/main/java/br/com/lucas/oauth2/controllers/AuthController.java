package br.com.lucas.oauth2.controllers;

import br.com.lucas.oauth2.DTOs.LoginRequestDTO;
import br.com.lucas.oauth2.DTOs.TokenReponseDTO;
import br.com.lucas.oauth2.repositories.UserRepository;
import br.com.lucas.oauth2.services.TokenService;
import br.com.lucas.oauth2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenReponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO)
    {

        var user = userRepository.findByUsername(loginRequestDTO.username());

        if(user.isEmpty() || !userService.isLoginCorrect(loginRequestDTO, user.get(), bCryptPasswordEncoder)){
            throw new BadCredentialsException("user of password is invalid!");
        }


        TokenReponseDTO tokenReponseDTO = tokenService.generateToken(user.get());
        return ResponseEntity.ok().body(tokenReponseDTO);

    }


}
