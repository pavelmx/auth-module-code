package com.okta.spring.AuthorizationServerApplication.controller;

import com.okta.spring.AuthorizationServerApplication.entity.RestError;
import com.okta.spring.AuthorizationServerApplication.entity.User;
import com.okta.spring.AuthorizationServerApplication.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userDetailsService;


    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User user, @RequestParam String roleNames) {
        try {
            return new ResponseEntity<>(userDetailsService.create(user, roleNames), HttpStatus.CREATED);
        } catch (AccountException e) {
            return new ResponseEntity<>(new RestError(e.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (NotFoundException e) {
            return new ResponseEntity<>(new RestError(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
