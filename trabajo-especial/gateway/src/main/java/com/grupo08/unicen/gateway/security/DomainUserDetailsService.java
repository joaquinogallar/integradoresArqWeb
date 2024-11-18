package com.grupo08.unicen.gateway.security;

import com.grupo08.unicen.gateway.entity.Authority;
import com.grupo08.unicen.gateway.entity.User;
import com.grupo08.unicen.gateway.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username ) {
        log.debug("Authenticating {}", username);

        return userRepository
                .findOneWithAuthoritiesByUsernameIgnoreCase( username.toLowerCase() )
                .map( this::createSpringSecurityUser )
                .orElseThrow( () -> new UsernameNotFoundException( "El usuario " + username + " no existe" ) );
    }

    private UserDetails createSpringSecurityUser( User user ) {
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map( Authority::getName )
                .map( SimpleGrantedAuthority::new )
                .collect( Collectors.toList() );
        return new org.springframework.security.core.userdetails.User( user.getUsername(), user.getPassword(), grantedAuthorities );
    }


}
