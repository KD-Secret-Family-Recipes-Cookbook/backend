package com.tjclawson.secretrecipe.services;

import com.tjclawson.secretrecipe.models.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();
}
