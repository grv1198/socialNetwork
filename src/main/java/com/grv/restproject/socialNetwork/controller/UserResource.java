package com.grv.restproject.socialNetwork.controller;

import com.grv.restproject.socialNetwork.exception.UserNotFoundException;
import com.grv.restproject.socialNetwork.user.service.User;
import com.grv.restproject.socialNetwork.user.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> getAllUsers(){

        return userDaoService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUsers(@PathVariable Integer userId){

        User user =  userDaoService.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("ID : " + userId);
        }

        return user;

    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user){

       User savedUser =  userDaoService.addUser(user);
       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

       return ResponseEntity.created(location).build();
    }
}
