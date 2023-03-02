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


    public boolean createUser(User user){
        String email = user.getEmail();
        if(userRepository.findUserByEmail(email) != null) return false;
        user.setEnable(true);
        user.getRoleSet().add(Role.ROLE_USER);
        userRepository.save(user);
        log.info("Saving new User with email: " + email);
        return true;
    }
}
