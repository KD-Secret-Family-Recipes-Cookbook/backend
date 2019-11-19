package com.tjclawson.secretrecipe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    @Autowired
    TokenStore tokenStore;

    @GetMapping(value = "/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            String tokenValue = authorizationHeader.replace("Bearer","").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
    }
}
