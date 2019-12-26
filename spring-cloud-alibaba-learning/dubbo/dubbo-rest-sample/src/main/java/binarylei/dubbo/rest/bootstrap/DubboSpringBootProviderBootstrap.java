package binarylei.dubbo.rest.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Dubbo Spring Cloud Provider Bootstrap
 */
@EnableAutoConfiguration
public class DubboSpringBootProviderBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DubboSpringBootProviderBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
