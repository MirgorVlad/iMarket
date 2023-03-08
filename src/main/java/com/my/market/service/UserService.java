package com.my.market.service;

import com.my.market.model.User;
import com.my.market.model.enums.Role;
import com.my.market.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(User user){
        String email = user.getEmail();
        if(userRepository.findUserByEmail(email) != null) return false;
        user.setEnable(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoleSet().add(Role.ROLE_ADMIN);
        userRepository.save(user);
        log.info("Saving new User with email: " + email);
        return true;
    }

    public User findUserByEmail(String email){
        return  userRepository.findUserByEmail(email);
    }
    public User findUserById(int id){
        return  userRepository.findUserById(id);
    }
}
