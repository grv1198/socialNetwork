package com.grv.restproject.socialNetwork.controller;

import com.grv.restproject.socialNetwork.user.service.User;
import com.grv.restproject.socialNetwork.user.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gaurav on 14/11/18.
 */

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

        return userDaoService.getUserById(userId);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        userDaoService.addUser(user);
    }
}
