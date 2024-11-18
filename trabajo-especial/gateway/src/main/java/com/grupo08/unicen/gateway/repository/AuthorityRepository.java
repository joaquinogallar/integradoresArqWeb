package com.grupo08.unicen.gateway.repository;

import com.grupo08.unicen.gateway.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
