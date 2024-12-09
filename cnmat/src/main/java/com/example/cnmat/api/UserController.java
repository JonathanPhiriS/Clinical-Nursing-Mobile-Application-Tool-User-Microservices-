package com.example.cnmat.api;

import com.example.cnmat.model.User;
import com.example.cnmat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Registration endpoint
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String message = userService.registerUser(user);
        return ResponseEntity.ok(message);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String message = userService.authenticateUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(message);
    }

    // Update Profile endpoint
    @PutMapping("/{id}/profile")
    public ResponseEntity<String> updateUserProfile(@PathVariable Long id, @RequestBody User user) {
        String message = userService.updateUserProfile(id, user);
        return ResponseEntity.ok(message);
    }

    // View Profile endpoint
    @GetMapping("/{id}/profile")
    public ResponseEntity<User> viewUserProfile(@PathVariable Long id) {
        User user = userService.viewUserProfile(id);
        return ResponseEntity.ok(user); // Return the full user details for profile viewing
    }
}
