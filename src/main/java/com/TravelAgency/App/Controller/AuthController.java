package com.TravelAgency.App.Controller;

import com.TravelAgency.App.Model.User;
import com.TravelAgency.App.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String result = authService.registerUser(user.getEmail());

        if (result.equals("Email already exists")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String result = authService.loginUser(user.getEmail());

        if (result.equals("Email not registered") || result.equals("Invalid password")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }
}