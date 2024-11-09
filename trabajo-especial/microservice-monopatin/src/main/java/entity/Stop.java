package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String location;

    private String address;
}
