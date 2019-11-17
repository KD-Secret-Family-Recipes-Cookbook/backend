package com.tjclawson.secretrecipe;

import com.tjclawson.secretrecipe.models.User;
import com.tjclawson.secretrecipe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {

        List<User> users = userService.findAll();
        for (User u : users) {
            u.setPassword(u.getPassword());
        }
    }
}
