package com.desafio.order.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order API")
                        .version("1.0")
                        .description("API para gerenciar pedidos utilizando RabbitMQ, MongoDB e Redis."));
    }
}
