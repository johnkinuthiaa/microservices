package com.slippery.microservices;

import org.springframework.boot.SpringApplication;

public class TestMicroServicesApplication {

    public static void main(String[] args) {
        SpringApplication.from(MicroServicesApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
