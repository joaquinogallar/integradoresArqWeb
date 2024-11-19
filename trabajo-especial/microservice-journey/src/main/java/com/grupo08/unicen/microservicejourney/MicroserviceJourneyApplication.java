package com.grupo08.unicen.microservicejourney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceJourneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceJourneyApplication.class, args);
    }

}
