package br.com.lucas.oauth2.services;

import br.com.lucas.oauth2.DTOs.TokenReponseDTO;
import br.com.lucas.oauth2.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${jwt.private.key}")
    private String jwtKey;

    public TokenReponseDTO generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtKey);

            long expiresIn = 500L;

            String token = JWT.create()
                    .withIssuer("oauth2")
                    .withSubject(user.getUsername())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(expiresIn))
                    .sign(algorithm);

            return new TokenReponseDTO(token, expiresIn);

        } catch (JWTCreationException exception){
            throw new RuntimeException("Error while authenticating");
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(jwtKey);

            return JWT.require(algorithm)
                    .withIssuer("oauth2")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            return null;
        }

    }

}
