package org.example.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Info info = new Info()
                .title("Dynamika Test Task")
                .version("1.0")
                .description("Интерфейс для работы с приложением для заказа книг в библиотеке.");
        return new OpenAPI()
                .info(info);
    }
}
