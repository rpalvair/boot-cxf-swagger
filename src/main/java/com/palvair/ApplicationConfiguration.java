package com.palvair;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class ApplicationConfiguration {

    @Autowired
    private Bus bus;

    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endPoint = new JAXRSServerFactoryBean();
        endPoint.setBus(bus);
        endPoint.setResourceClasses(HelloWorldServiceImpl.class);
        endPoint.setAddress("/");
        endPoint.setFeatures(Collections.singletonList(new Swagger2Feature()));
        endPoint.setProvider(new JacksonJsonProvider());
        return endPoint.create();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }
}
