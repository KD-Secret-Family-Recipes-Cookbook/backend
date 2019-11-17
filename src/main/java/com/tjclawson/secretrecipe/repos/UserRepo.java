package com.tjclawson.secretrecipe.repos;

import com.tjclawson.secretrecipe.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
