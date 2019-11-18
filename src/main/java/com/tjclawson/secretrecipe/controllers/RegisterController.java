package com.tjclawson.secretrecipe.controllers;

import com.tjclawson.secretrecipe.models.User;
import com.tjclawson.secretrecipe.models.UserMinimum;
import com.tjclawson.secretrecipe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/createnewuser",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody UserMinimum newminuser) throws URISyntaxException {
        // Create the user
        User newuser = new User();

        newuser.setUsername(newminuser.getUsername());
        newuser.setPasswordNotEncrypt(newminuser.getPassword());
        newuser.setUseremail(newminuser.getUseremail());

        userService.save(newuser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}


