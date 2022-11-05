package org.binar.bioskop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
            .info(new io.swagger.v3.oas.models.info.Info()
                .title("Reservasi Bioskop - Joko Prabowo")
                .version("V1.0.0")
                .description("Berikut ini merupakan dokumentasi endpoint untuk back end Reservasi Bioskop Chapter 4 - 8"));
    }
}
