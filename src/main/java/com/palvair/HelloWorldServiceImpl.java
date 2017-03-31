package com.palvair;

import io.swagger.annotations.*;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Service
@Path("/sayHello")
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
            )}
    )
    @GET
    @Path("/{a}")
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String sayHello(@ApiParam(name = "test", value = "test") String a) {
        return "Hello " + a + ", Welcome to CXF RS Spring Boot World!!!";
    }
}
