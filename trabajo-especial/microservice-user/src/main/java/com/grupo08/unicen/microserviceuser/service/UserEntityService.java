package com.grupo08.unicen.microserviceuser.service;

import com.grupo08.unicen.microserviceuser.model.MonopatinDto;
import com.grupo08.unicen.microserviceuser.client.JourneyFeignClient;
import com.grupo08.unicen.microserviceuser.client.MonopatinFeignClient;

import com.grupo08.unicen.microserviceuser.dto.UserEntityDto;
import com.grupo08.unicen.microserviceuser.entity.Account;
import com.grupo08.unicen.microserviceuser.entity.UserEntity;
import com.grupo08.unicen.microserviceuser.exception.UserNotFoundException;
import com.grupo08.unicen.microserviceuser.model.JourneyDto;
import com.grupo08.unicen.microserviceuser.repository.AccountRepository;
import com.grupo08.unicen.microserviceuser.repository.UserEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserEntityService {

    @Autowired
   private UserEntityRepository userEntityRepository;

   @Autowired
   private JourneyFeignClient journeyFeignClient;

   @Autowired 
  private MonopatinFeignClient monopatinFeignClient;

   @Autowired
   private AccountRepository accountRepository ;
    // basic methods
    public ResponseEntity<List<UserEntityDto>> getAllUsers() {
        try {
            List<UserEntity> users = userEntityRepository.findAll();
            List<UserEntityDto> userDtos = new ArrayList<>();
            users.forEach(u -> userDtos.add(new UserEntityDto(u.getId(), u.getName(), u.getLastname(), u.getEmail(), u.getPhoneNumber(), u.getX(), u.getY())));
            return ResponseEntity.ok(userDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<UserEntityDto> getUserById(UUID userId) {
        UserEntity u = userEntityRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        UserEntityDto userDto = new UserEntityDto(u.getId(), u.getName(), u.getLastname(), u.getEmail(), u.getPhoneNumber(), u.getX(), u.getY());
        return ResponseEntity.ok(userDto);
    }

    public ResponseEntity<String> createUser(UserEntityDto newUser) {
        try {
            userEntityRepository.save(new UserEntity(newUser));
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error: " + e);
        }
    }

    public ResponseEntity<UserEntityDto> deleteUserById(UUID userId) {
        UserEntity u = userEntityRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        UserEntityDto userDto = new UserEntityDto(u.getId(), u.getName(), u.getLastname(), u.getEmail(), u.getPhoneNumber(), u.getX(), u.getY());
        userEntityRepository.delete(u);

        return ResponseEntity.ok(userDto);
    }

    public ResponseEntity<JourneyDto> activateMonopatinByQr(UUID monopatinId, UUID userId, UUID accountId) {
        try {
            UserEntity user = userEntityRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId.toString()));
            Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException(accountId.toString()));

            if(!user.getAccounts().contains(account))
                throw new RuntimeException(accountId.toString());

            MonopatinDto monopatin = monopatinFeignClient.getMonopatinById(monopatinId).getBody();
            return journeyFeignClient.createViaje(monopatinId, userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<List<MonopatinDto>> getNearMonopatines(UUID id,int rangoMetros) {
       UserEntity u = userEntityRepository.findById(id).orElse(null);
       return monopatinFeignClient.getNearMonopatines(u.getX(),u.getY(), rangoMetros) ;
    }

    public ResponseEntity<UserEntityDto> editUser(UUID userId, UserEntityDto user) {
       UserEntity u = userEntityRepository.findById(userId).orElse(null);
       try {
        if(u!=null){
            u.setEmail(user.getEmail());
            u.setName(user.getName());
            u.setX(user.getX());
            u.setY(user.getY());
            userEntityRepository.save(u);
            ResponseEntity.ok(user);
        }
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
       }return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
       .body(null);
    }

    public String addAccount(UUID userId, UUID accountId) {
        UserEntity u = userEntityRepository.findById(userId).orElse(null);
        Account a = accountRepository.findById(accountId).orElse(null);
        try {
            if(u!=null && a!=null){
                u.getAccounts().add(a);
                userEntityRepository.save(u);
                return "Account added";
            }
        } catch (Exception e) {
            return "Error: " + e;
        }
        return "Error";
    }

}
