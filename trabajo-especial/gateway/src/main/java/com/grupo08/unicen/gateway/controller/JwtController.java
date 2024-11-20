package com.grupo08.unicen.gateway.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo08.unicen.gateway.security.jwt.JwtFilter;
import com.grupo08.unicen.gateway.security.jwt.TokenProvider;
import com.grupo08.unicen.gateway.service.dto.login.LoginDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
@RequiredArgsConstructor
public class JwtController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping()
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginDto request ) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate( authenticationToken );
        SecurityContextHolder.getContext().setAuthentication( authentication );
        final var jwt = tokenProvider.createToken( authentication );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add( JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt );
        return new ResponseEntity<>( new JWTToken( jwt ), httpHeaders, HttpStatus.OK );
    }

    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
