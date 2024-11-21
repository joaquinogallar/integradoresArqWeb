package com.grupo08.unicen.microservicemonopatin;

import com.grupo08.unicen.microservicemonopatin.dto.MonopatinDto;
import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import com.grupo08.unicen.microservicemonopatin.entity.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class MicroserviceMonopatinApplicationTests {

    List<Monopatin> monopatines;

    @BeforeEach
    void contextLoads() {
        monopatines = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            monopatines.add(new Monopatin(UUID.randomUUID(), State.AVAILABLE, (int) (Math.random() * 100), (int) (Math.random() * 100), BigDecimal.ZERO, 0L));
        }
        monopatines.add(new Monopatin(UUID.randomUUID(), State.AVAILABLE, (int) (Math.random() * 100), (int) (Math.random() * 100), BigDecimal.ZERO, -2L));
    }

    @Test
    public void testUseTimeMayorACero() {
        for (Monopatin m : monopatines) {
            Assertions.assertFalse(m.getUseTime() > 0L, "El tiempo de uso debe ser mayor a cero");
        }
    }

    @Test
    public void testMonopatinCreadoCorrectamente() {
        MonopatinDto m = new MonopatinDto(UUID.randomUUID(), State.AVAILABLE, (int) (Math.random() * 100), (int) (Math.random() * 100), BigDecimal.ZERO, 0L);
        Assertions.assertNotNull(m, "El objeto de tipo MonopatinDto no puede ser nulo");
    }

    @Test
    public void testKmTraveledMayorACero() {
        for (Monopatin m : monopatines) {
            Assertions.assertFalse(m.getKmTraveled().compareTo(BigDecimal.ZERO) > 0);
        }
    }
}
