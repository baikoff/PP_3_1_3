package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT c FROM User c", User.class).getResultList();
    }


    public void save(User user) {
         entityManager.persist(user);
    }


    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }


    public User update(User user, Long id) {
        return entityManager.merge(user);
    }


    public void delete(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User findByName(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u from User u WHERE u.username=:username"
                ,User.class);
        return query.setParameter("username", username).getSingleResult();
    }


}