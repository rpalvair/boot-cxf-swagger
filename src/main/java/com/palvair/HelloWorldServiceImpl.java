package com.palvair;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "/sayHello", description = "Hello world")
public class HelloWorldServiceImpl implements HelloWorldService {

    @ApiOperation(
            value = "Renvoie un message",
            notes = "Renvoie un message",
            response = String.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Message bien reçu",
                    response = String.class
            ) }
    )
    @Override
    public String sayHello(@ApiParam(name = "test", value = "test") String a) {
        return "Hello " + a + ", Welcome to CXF RS Spring Boot World!!!";
    }
}
