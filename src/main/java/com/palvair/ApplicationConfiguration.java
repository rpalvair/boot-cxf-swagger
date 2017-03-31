package com.palvair;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ApplicationConfiguration {

    @Autowired
    private Bus bus;

    @Autowired
    private ApplicationContext applicationContext;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endPoint = new JAXRSServerFactoryBean();
        endPoint.setBus(bus);
        final List<Class<?>> resourceClasses = getResourceClasses();
        endPoint.setResourceClasses(resourceClasses);
        endPoint.setAddress("/");
        endPoint.setFeatures(Collections.singletonList(new Swagger2Feature()));
        endPoint.setProvider(new JacksonJsonProvider());
        return endPoint.create();
    }

    private List<Class<?>> getResourceClasses() {
        return Stream.of(applicationContext.getBeanNamesForAnnotation(Service.class))
                .filter(s -> applicationContext.findAnnotationOnBean(s, Path.class) != null)
                .map(s -> applicationContext.getBean(s).getClass())
                .filter(Objects::nonNull)
                .peek(aClass -> LOGGER.debug("class {}", aClass))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }


}
