package com.grupo08.unicen.gateway.security.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;


public class JwtFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger( JwtFilter.class );

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;

    public JwtFilter( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.debug("Processing request for URI: {}", request.getRequestURI());
        String jwt = resolveToken(request);
        try {
            if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
                log.debug("Token is valid for URI: {}", request.getRequestURI());
                Authentication authentication = tokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (ExpiredJwtException e) {
            log.info("REST request UNAUTHORIZED - La sesión ha expirado.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(new JwtErrorDTO().toJson());
            return;
        }
        filterChain.doFilter(request, response);  // Permite que las solicitudes pasen
    }



    private String resolveToken( HttpServletRequest request ) {
        String bearerToken = request.getHeader( AUTHORIZATION_HEADER );
        if ( StringUtils.hasText( bearerToken ) && bearerToken.startsWith( "Bearer " ) ) {
            return bearerToken.substring(7 );
        }
        return null;
    }

    @Getter
    private static class JwtErrorDTO {
        private final String message = "Token expired";
        private final String date = LocalDateTime.now().toString();

        public JwtErrorDTO(){}

        public String toJson() {
            try {
                return new ObjectMapper().writeValueAsString(this);
            } catch (RuntimeException | JsonProcessingException ex ) {
                return String.format("{ message: %s }", this.message );
            }
        }
    }
}
