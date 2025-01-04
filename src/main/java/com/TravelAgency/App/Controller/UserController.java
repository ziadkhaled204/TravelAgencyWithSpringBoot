package com.TravelAgency.App.Controller;

import com.TravelAgency.App.Model.User;
import com.TravelAgency.App.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String result = userService.createUser(user);
        if (result.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            userService.updateUser(id, user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return new ResponseEntity<>(user.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
