package com.lulu.gamification.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI gamificationOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gamification API")
                        .description("API para sistema de gamificação com usuários e missões")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento")
                                .email("dev@gamification.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Completa")
                        .url("https://gamification.com/docs"));
    }
}