package com.maveric.userservice.controllers;

import com.maveric.userservice.dto.Userdto;
import com.maveric.userservice.dto.UserResponse;
import com.maveric.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    

    @PutMapping("/users/{userId}")
    public  ResponseEntity<UserResponse> updateUser(@Valid @RequestBody Userdto updateUser, @PathVariable("userId") String userId){
        log.info("Updating user");
        return new ResponseEntity<UserResponse>(userService.updateUser(userId,updateUser),HttpStatus.OK);
    }

    
}
