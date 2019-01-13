package com.grv.restproject.socialNetwork.controller;

import com.grv.restproject.socialNetwork.exception.UserNotFoundException;
import com.grv.restproject.socialNetwork.model.User;
import com.grv.restproject.socialNetwork.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public Resource<User> getUsers(@PathVariable Integer userId){

        User user =  userDaoService.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("ID : " + userId);
        }

        Resource<User> resource = new Resource<>(user);

        ControllerLinkBuilder linkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());

        resource.add(linkBuilder.withRel("all-users"));
        return resource;

    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){

       User savedUser =  userDaoService.addUser(user);
       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

       return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userId}")
    public User deleteUser(@PathVariable Integer userId){

        User user =  userDaoService.deleteUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("ID : " + userId);
        }

        return user;

    }
}
