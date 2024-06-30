package com.task.soccer.firstleague.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI productServiceAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Soccer First League API")
                        .description("API for generating game schedules for the Soccer First League")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Support Team")
                                .email("support@soccer.com")
                                .url("https://www.soccer.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Refer the Soccer first league GitHub")
                        .url("https://github.com/vishnuvardhanreddy30/soccer-first-league"));
    }
}
