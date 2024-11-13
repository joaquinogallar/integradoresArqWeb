package com.grupo08.unicen.microservicemonopatin.dto;

import com.grupo08.unicen.microservicemonopatin.entity.Stop;

public class StopDto {
    
    private int x;
    private int y;

    private String address;

    public StopDto(Stop s){
        this.x = s.getX();
        this.y= s.getY();
        this.address = s.getAddress();
    }
}
