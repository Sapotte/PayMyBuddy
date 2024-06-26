package com.paymybuddy.services;

import com.paymybuddy.controllers.dto.PostUser;
import com.paymybuddy.db.models.User;
import com.paymybuddy.db.repositories.UserRepository;
import com.paymybuddy.services.mappers.UserServiceMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UserRepository userRepository;

    private final UserServiceMapperImpl userServiceMapper = new UserServiceMapperImpl();

    public Boolean createUserAccount(PostUser postUser) {
        if(checkIfEmailExists(postUser.getEmail())) {
            return false;
        } else {
            // Encode password
            postUser.setPassword(new BCryptPasswordEncoder().encode(postUser.getPassword()));
            User user =  userServiceMapper.mapPostUserToUser(postUser, (float) 0);
            userRepository.save(user);
            return true;
        }
    }

    /** Method to check if an account has already been created with this email
     * @param email, id user
     * @return true if account is already registered
     */
    private boolean checkIfEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
