package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    void save(User user);

    User findById(Long id);

    User update(User user, Long id);

    void delete(Long id);

    User findByName(String username);
}
