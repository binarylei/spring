package com.github.binarylei.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application2 {

    public Application2() {
        System.out.println();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application2.class, args);
    }

}
