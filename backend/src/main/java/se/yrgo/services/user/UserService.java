package se.yrgo.services.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.UserRepository;
import se.yrgo.domain.User;
import se.yrgo.dto.user.UpdateUserRequest;
import se.yrgo.dto.user.UserResponse;
import se.yrgo.exceptions.user.UserAlreadyExistsException;
import se.yrgo.exceptions.user.UserNotFoundException;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // CREATE

    public UserResponse createUser(String name, String password) {

        if (repository.existsByName(name)) {
            throw new UserAlreadyExistsException(
                    "User with name '" + name + "' already exists");
        }

        User user = new User(name, password);

        return toResponse(repository.save(user));
    }

    // READ

    public UserResponse getUserById(Long id) throws UserNotFoundException {

        return toResponse(repository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    public UserResponse getUserByUsername(String name) throws UserNotFoundException {

        return toResponse(repository.findByName(name).orElseThrow(() -> new UserNotFoundException(name)));
    }

    public List<UserResponse> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // UPDATE

    public UserResponse updateUser(Long id, UpdateUserRequest request)
            throws UserNotFoundException {

        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setName(request.name());
        user.setPassword(request.password());

        return toResponse(repository.save(user));
    }

    // DELETE

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    // HELP METHODS

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName());
    }

}