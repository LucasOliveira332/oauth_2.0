package br.com.lucas.oauth2.services;

import br.com.lucas.oauth2.DTOs.LoginRequestDTO;
import br.com.lucas.oauth2.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public boolean isLoginCorrect(LoginRequestDTO loginRequestDTO, User user, PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(loginRequestDTO.password(), user.getPassword());
    }
}
