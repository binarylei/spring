package com.binarylei.jaxrs.helloword;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author binarylei
 * @version 2019-09-19
 * @since 2.0.0
 */
@Path("/")
public class HelloController {
    @GET
    @Path("/hello")
    public String hello() {
        return "hello, world!";
    }

    @GET
    @Path("/echo/{msg}")
    public String echo(@PathParam("msg") String msg) {
        return msg;
    }

    @GET
    @Path("/user/{age}")
    // Produces：响应的媒体类型为xml
    @Produces(MediaType.TEXT_XML)
    public String user(@PathParam("age") int age) {
        return "<User>" + "<Age>" + age + "</Age>" + "</User>";
    }

}

