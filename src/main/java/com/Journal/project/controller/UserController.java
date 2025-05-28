package com.Journal.project.controller;

import com.Journal.project.repository.UserRepos;
import com.Journal.project.service.UserService;
import com.Journal.project.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepos userRepository;

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userOne = userService.findByUserName(userName);
            userOne.setUserName(userName);
            userOne.setPassword(user.getPassword());
            userService.saveNewUser(userOne);
            return new ResponseEntity<>(userOne, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hi" + authentication.getName(), HttpStatus.OK);
    }
}
