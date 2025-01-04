package com.TravelAgency.App.Services;

import com.TravelAgency.App.Model.User;
import com.TravelAgency.App.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(String email) {

        if (userRepository.existsByEmail(email)) {
            return "Email already exists";
        }

        // Create and save the new user
        User newUser = new User();
        newUser.setEmail(email);
        userRepository.save(newUser);

        return "Registration successful";
    }

    public String loginUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return "Email not registered";
        }
        return "Login successful";}
}