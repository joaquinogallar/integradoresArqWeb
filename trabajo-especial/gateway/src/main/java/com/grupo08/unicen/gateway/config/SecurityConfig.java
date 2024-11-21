package com.grupo08.unicen.gateway.config;

import com.grupo08.unicen.gateway.security.AuthorityConstant;
import com.grupo08.unicen.gateway.security.jwt.JwtFilter;
import com.grupo08.unicen.gateway.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/users").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/monopatines/**").hasAuthority(AuthorityConstant._USER)
                        .requestMatchers(HttpMethod.POST, "/api/monopatines/**").hasAuthority(AuthorityConstant._ADMIN)
                        .requestMatchers(HttpMethod.DELETE, "/api/monopatines/**").hasAuthority(AuthorityConstant._ADMIN)

                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAuthority(AuthorityConstant._ADMIN)
                        .requestMatchers(HttpMethod.POST, "/api/users/**").hasAuthority(AuthorityConstant._USER)
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAuthority(AuthorityConstant._ADMIN)

                        .requestMatchers(HttpMethod.GET, "/api/accounts/**").hasAuthority(AuthorityConstant._ADMIN)
                        .requestMatchers(HttpMethod.POST, "/api/accounts/**").hasAuthority(AuthorityConstant._USER)
                        .requestMatchers(HttpMethod.DELETE, "/api/accounts/**").hasAuthority(AuthorityConstant._ADMIN)

                        .requestMatchers(HttpMethod.GET, "/api/stops/**").hasAuthority(AuthorityConstant._USER)
                        .requestMatchers(HttpMethod.POST, "/api/stops/**").hasAuthority(AuthorityConstant._ADMIN)
                        .requestMatchers(HttpMethod.DELETE, "/api/stops/**").hasAuthority(AuthorityConstant._ADMIN)

                        .requestMatchers("/api/maintenances/**").hasAuthority(AuthorityConstant._ADMIN)
                        .requestMatchers("/api/journeys/**").hasAuthority(AuthorityConstant._USER)
                        .requestMatchers("/api/fees/**").hasAuthority(AuthorityConstant._USER)
                        .requestMatchers("/api/pauses/**").hasAuthority(AuthorityConstant._USER)

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new JwtFilter(this.tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
