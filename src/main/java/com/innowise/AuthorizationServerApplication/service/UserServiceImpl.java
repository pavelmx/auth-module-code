package com.innowise.AuthorizationServerApplication.service;


import com.innowise.AuthorizationServerApplication.entity.Role;
import com.innowise.AuthorizationServerApplication.entity.User;
import com.innowise.AuthorizationServerApplication.repository.RoleRepository;
import com.innowise.AuthorizationServerApplication.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: '" + username + "' not found."));
        return user;
    }

    @Override
    public User create(User user, String roleNames) throws AccountException, NotFoundException {
        if(userRepo.existsByUsername(user.getUsername())){
            throw new AccountException("Username '" + user.getUsername() + "' already exists");
        }
        Set<Role> roles = new HashSet<>();
        //for several roles
        /*for (String role: roleNames) {
            roles.add(roleRepo.findByName(role)
                    .orElseThrow(() -> new NotFoundException("Role with name '" + role + "' not found")));
        }*/
        //for 1 role
        roles.add(roleRepo.findByName(roleNames).get());
        user.setRoles(roles);
        user.setCreated(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }


}