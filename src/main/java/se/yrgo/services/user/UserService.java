package se.yrgo.services.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.UserRepository;
import se.yrgo.domain.User;
import se.yrgo.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser (String username, String password) {

        User user = new User(username, password);

        return repository.save(user);
    }

    public User getUserById(Long id) throws UserNotFoundException {

        return repository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(id));
    }

    public User getUserByUsername(String name) throws UserNotFoundException {

        return repository.findByUserName(name)
                .orElseThrow(() -> new UserNotFoundException(name));
    }

    public void deleteUser(Long id) {

        repository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}