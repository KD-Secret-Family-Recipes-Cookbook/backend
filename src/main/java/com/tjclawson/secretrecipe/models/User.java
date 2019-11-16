package com.tjclawson.secretrecipe.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    @Email(message = "Email must be in valid format")
    private String useremail;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRecipe> userrecipes = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, @Email(message = "Email must be in valid format") String useremail, List<UserRecipe> userrecipes) {
        this.username = username;
        this.password = password;
        this.useremail = useremail;
        for (UserRecipe ur : userrecipes) {
            ur.setUser(this);
        }
        this.userrecipes = userrecipes;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {

        if (username == null) {
            return null;
        } else {
            return username.toLowerCase();
        }
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUseremail() {
        if (useremail == null) {
            return null;
        } else {
            return useremail.toLowerCase();
        }
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail.toLowerCase();
    }

    public List<UserRecipe> getUserrecipes() {
        return userrecipes;
    }

    public void setUserrecipes(List<UserRecipe> userrecipes) {
        this.userrecipes = userrecipes;
    }
}
