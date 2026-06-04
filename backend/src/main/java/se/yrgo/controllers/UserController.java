package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.dataaccess.UserRepository;
import se.yrgo.domain.User;
import se.yrgo.dto.user.CreateUserRequest;
import se.yrgo.dto.user.UpdateUserRequest;
import se.yrgo.dto.user.UserResponse;
import se.yrgo.exceptions.UserAlreadyExistsException;
import se.yrgo.exceptions.UserNotFoundException;
import se.yrgo.services.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService service;
    private final UserRepository repository;

    public UserController(UserService service, UserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    // CREATE

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request) {


        if (repository.existsByName(request.name())) {
            throw new UserAlreadyExistsException(
                    "User with name '" + request.name() + "' already exists");
        }

        User user = service.createUser(
                request.name(),
                request.password()
        );
        return toResponse(user);
    }

    // READ

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return service.getAllUsers()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) throws UserNotFoundException {
        return service.getUserById(id);
    }

    // UPDATE

    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request)
            throws UserNotFoundException {

        User updatedUser = service.updateUser(
                id,
                request
        );

        return toResponse(updatedUser);
    }

    // DELETE

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }

    // HELP METHOD

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName());
    }

}
