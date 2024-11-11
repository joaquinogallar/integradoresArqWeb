package com.grupo08.unicen.microservicepayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicePaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicePaymentApplication.class, args);
    }

}
