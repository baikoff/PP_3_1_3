package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;


import java.security.Principal;
import java.util.List;

public interface UserDAO {

    void createUser(User user);
    User readUser(Long id);
    void updateUser(User user);
    void deleteUser(Long id);
    User getUserByName(String name);
    List<User> allUsers();
    boolean isAllowed(Long id, Principal principal);
}
