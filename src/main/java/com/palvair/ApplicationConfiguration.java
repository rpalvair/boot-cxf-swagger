package com.palvair;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationConfiguration {


    @Bean
    public Swagger2Feature swaggerFeature() {
        return new Swagger2Feature();
    }

    @Bean
    public JacksonJsonProvider jacksonJsonProvider() {
        return new JacksonJsonProvider();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }


}
