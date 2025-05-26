package com.Journal.project.service;

import com.Journal.project.repository.UserRepos;
import com.Journal.project.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepos userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add("USER");
        userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId objectId){
        return userRepository.findById(objectId);
    }

    public boolean deleteById(ObjectId objectId){
        userRepository.deleteById(objectId);
        return true;
    }

    public User findByUserName(String username){
       return userRepository.findByUserName(username);
    }

}
