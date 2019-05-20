package com.innowise.AuthorizationServerApplication.service;

import com.innowise.AuthorizationServerApplication.entity.User;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.login.AccountException;

public interface UserService {

    UserDetails loadUserByUsername(String username);

    User create(User user, String roleNames) throws AccountException, NotFoundException;
}
