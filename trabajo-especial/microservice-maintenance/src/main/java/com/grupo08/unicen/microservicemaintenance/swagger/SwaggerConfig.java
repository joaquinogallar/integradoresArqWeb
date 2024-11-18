package com.grupo08.unicen.microservicemaintenance.swagger;


    
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@OpenAPIDefinition()
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(new Info()
                .title("Microservicios de mantenimiento")
                .version("1.0.0")
                .description("Api para poder registrar reportes y acciones de mantenimiento de monopatines"));
    }
}
