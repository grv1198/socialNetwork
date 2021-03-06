package com.grv.restproject.socialNetwork.controller;

import com.grv.restproject.socialNetwork.exception.UserNotFoundException;
import com.grv.restproject.socialNetwork.model.Post;
import com.grv.restproject.socialNetwork.model.User;
import com.grv.restproject.socialNetwork.service.PostRepository;
import com.grv.restproject.socialNetwork.service.UserRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class UserJPAResource {

    @Autowired
    private UserRepsitory userRepsitory;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> getAllUsers(){

        return userRepsitory.findAll();
    }

    @GetMapping("/jpa/users/{userId}")
    public Resource<User> getUsers(@PathVariable Integer userId){

        Optional<User> user =  userRepsitory.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("ID : " + userId);
        }

        Resource<User> resource = new Resource<>(user.get());

        ControllerLinkBuilder linkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());

        resource.add(linkBuilder.withRel("all-users"));
        return resource;

    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){

       User savedUser =  userRepsitory.save(user);
       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

       return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{userId}/posts")
    public List<Post> getUserPost(@PathVariable Integer userId){

        Optional<User> user =  userRepsitory.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("ID : " + userId);
        }

        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable Integer id,  @Valid @RequestBody Post post){

        Optional<User> user =  userRepsitory.findById(id);
        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{userId}")
    public void deleteUser(@PathVariable Integer userId){

        userRepsitory.deleteById(userId);
    }
}
