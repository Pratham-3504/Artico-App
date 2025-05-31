package com.Journal.project.controller;

import com.Journal.project.cache.AppCache;
import com.Journal.project.entity.User;
import com.Journal.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @GetMapping("/clean-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
