package com.grupo08.unicen.microservicemaintenance.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("MICROSERVICE-MONOPATIN")
public interface MonopatinFeignClient {
    
}
