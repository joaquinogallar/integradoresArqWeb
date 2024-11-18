package com.grupo08.unicen.microservicemonopatin.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


public class SwaggerConfig {
    
    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(new Info()
                .title("Microservicios de monopatin")
                .version("1.0.0")
                .description("Api para poder realizar acciones sobre monopatines y paradas."));
    }
}

