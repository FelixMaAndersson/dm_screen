package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.User;
import se.yrgo.dto.user.CreateUserRequest;
import se.yrgo.dto.user.UpdateUserRequest;
import se.yrgo.dto.user.UserResponse;
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
    public List<UserResponse> getAllUsers() {
        return service.getAllUsers()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName()
                ))
                .toList();
    }

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        User user = service.createUser(
                request.name(),
                request.password()
        );
        return new UserResponse(user.getId(), user.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) throws UserNotFoundException {
        return service.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request)
            throws UserNotFoundException {

        User updatedUser = service.updateUser(
                id,
                request.name(),
                request.password()
        );

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getName()
        );
    }

}
