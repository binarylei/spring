package com.binarylei.feign;

import feign.Feign;
import feign.gson.GsonDecoder;

import java.util.List;

/**
 * @author binarylei
 * @version 2019-09-19
 * @since 2.0.0
 */
public class MyApp {

    public static void main(String... args) {
        GitHub github = Feign.builder()
                .decoder(new GsonDecoder())
                .target(GitHub.class, "https://api.github.com");

        // Fetch and print a list of the contributors to this library.
        List<Contributor> contributors = github.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
    }
}