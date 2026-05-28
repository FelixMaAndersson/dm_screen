package se.yrgo.dataaccess;

import java.util.List;

import se.yrgo.domain.User;
import se.yrgo.exceptions.UserNotFoundException;

public interface UserDao {
public void createUser (User user);
public void updateUser (User user);
public void deleteUser (User user);
public User getUserById (int id);
public User getUserByName (String name);
}
