package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void delete(Long id) {
        userDAO.delete(id);
    }

    @Transactional
    public void saveUser(User user) {
        userDAO.save(user);
    }

    @Transactional
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    public User update(User user, Long id) {
        return userDAO.update(user, id);
    }

    @Transactional
    public User findBiName(String name) {
        return userDAO.findByName(name);
    }
}
