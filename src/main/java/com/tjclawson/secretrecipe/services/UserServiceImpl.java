package com.tjclawson.secretrecipe.services;

import com.tjclawson.secretrecipe.models.User;
import com.tjclawson.secretrecipe.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setUseremail(user.getUseremail());
        if (user.getRecipes() != null) {
            newUser.setRecipes(user.getRecipes());
        }
        return userRepo.save(newUser);
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
