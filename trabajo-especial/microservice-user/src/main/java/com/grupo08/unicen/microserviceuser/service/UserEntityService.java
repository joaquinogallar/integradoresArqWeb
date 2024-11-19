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
    private UserEntityRepository userEntityRepository;
    private JourneyFeignClient journeyFeignClient;
    private MonopatinFeignClient monopatinFeignClient;
    private AccountRepository accountRepository ;

    public UserEntityService(UserEntityRepository userEntityRepository, JourneyFeignClient journeyFeignClient, MonopatinFeignClient monopatinFeignClient, AccountRepository accountRepository) {
        this.userEntityRepository = userEntityRepository;
        this.journeyFeignClient = journeyFeignClient;
        this.monopatinFeignClient = monopatinFeignClient;
        this.accountRepository = accountRepository;
    }

    // basic methods
    public List<UserEntityDto> getAllUsers() {
        List<UserEntity> users = userEntityRepository.findAll();
        List<UserEntityDto> userDtos = new ArrayList<>();
        users.forEach(u -> userDtos.add(new UserEntityDto(u.getId(), u.getName(), u.getLastname(), u.getEmail(), u.getPhoneNumber(), u.getX(), u.getY())));
        return userDtos;
    }

    public UserEntityDto getUserById(UUID userId) {
        UserEntity u = userEntityRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        UserEntityDto userDto = new UserEntityDto(u.getId(), u.getName(), u.getLastname(), u.getEmail(), u.getPhoneNumber(), u.getX(), u.getY());
        return userDto;
    }

    public String createUser(UserEntityDto newUser) {
        userEntityRepository.save(new UserEntity(newUser));
        return "User created successfully";
    }

    public UserEntityDto deleteUserById(UUID userId) {
        UserEntity u = userEntityRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        UserEntityDto userDto = new UserEntityDto(u.getId(), u.getName(), u.getLastname(), u.getEmail(), u.getPhoneNumber(), u.getX(), u.getY());
        userEntityRepository.delete(u);

        return userDto;
    }

    public JourneyDto activateMonopatinByQr(UUID monopatinId, UUID userId, UUID accountId) {
        UserEntity user = userEntityRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId.toString()));
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException(accountId.toString()));

        if(!user.getAccounts().contains(account))
            throw new RuntimeException(accountId.toString());

        MonopatinDto monopatin = monopatinFeignClient.getMonopatinById(monopatinId).getBody();
        return journeyFeignClient.createViaje(monopatinId, userId).getBody();
    }

    public List<MonopatinDto> getNearMonopatines(UUID id,int rangoMetros) {
       UserEntity u = userEntityRepository.findById(id).orElse(null);
       return monopatinFeignClient.getNearMonopatines(u.getX(),u.getY(), rangoMetros).getBody();
    }

    public UserEntityDto editUser(UUID userId, UserEntityDto user) {
       UserEntity u = userEntityRepository.findById(userId).orElse(null);
        if(u!=null){
            u.setEmail(user.getEmail());
            u.setName(user.getName());
            u.setX(user.getX());
            u.setY(user.getY());
            userEntityRepository.save(u);
            return new UserEntityDto(u.getId(), u.getName(), u.getLastname(), u.getEmail(), u.getPhoneNumber(), u.getX(), u.getY());
        }
        return null;
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
