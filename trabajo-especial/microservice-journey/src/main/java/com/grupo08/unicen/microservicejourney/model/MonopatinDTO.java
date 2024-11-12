package com.grupo08.unicen.microservicejourney.model;

import java.math.BigDecimal;

public class MonopatinDTO {
    private int x ;
    private int y ;
     private BigDecimal kmTraveled;
    private Long useTime;
    public int getX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getX'");
    }
    public int getY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getY'");
    }
    public void setDisponible(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDisponible'");
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public BigDecimal getKmTraveled() {
        return kmTraveled;
    }
    public void setKmTraveled(BigDecimal kmTraveled) {
        this.kmTraveled = kmTraveled;
    }
    public Long getUseTime() {
        return useTime;
    }
    public void setUseTime(Long usado) {
        this.useTime += usado;
    }
    
}
