package com.grupo08.unicen.microservicemaintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceMaintenanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMaintenanceApplication.class, args);
    }

}
