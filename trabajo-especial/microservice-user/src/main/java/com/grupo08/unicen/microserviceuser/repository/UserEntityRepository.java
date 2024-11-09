package com.grupo08.unicen.microserviceuser.repository;

import com.grupo08.unicen.microserviceuser.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {
}
