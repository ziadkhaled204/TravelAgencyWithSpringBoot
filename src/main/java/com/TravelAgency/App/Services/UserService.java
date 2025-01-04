package com.TravelAgency.App.Services;

import com.TravelAgency.App.Model.User;
import com.TravelAgency.App.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    // Create a new user
    public String createUser(User user) {
        // Check if a user with the same email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "Error: A user with this email already exists.";
        }
        // Save the new user
        userRepository.save(user);
        return "User created successfully!";
    }

    // Update an existing user
    public String updateUser(int id, User user) {
        if (userRepository.existsById(id)) {
            user.setUserId(id);  // Set the ID to update the correct user
            userRepository.save(user);
            return "User updated successfully!";
        }
        return "User not found!";
    }

    // Delete a user
    public String deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted successfully!";
        }
        return "User not found!";
    }
}
