package com.standard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
@Profile({"local"})
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().pathMapping("/")
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        String title = "Standard app";
        String description = "Standard app";
        String version = "v1";
        Contact contact = new Contact("Renan Zazula",
                "", "renanzazula@gmail.com");
        return new ApiInfo(title,
                description,
                version,
                "",
                contact,
                "",
                "",
                new ArrayList<>());
    }

}
