package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.User;
import se.yrgo.exceptions.UserNotFoundException;
import se.yrgo.services.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.createUser(
                user.getName(),
                user.getPassword()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) throws UserNotFoundException {
        return service.getUserById(id);
    }

}
