package com.my.market.service;

import com.my.market.model.User;
import com.my.market.model.enums.Role;
import com.my.market.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void banUser(int id) {
        User user = findUserById(id);
        if(user!= null) {
            user.setEnable(!user.isEnable());
            userRepository.save(user);
        }
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        System.out.println(form);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoleSet().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoleSet().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }
}
