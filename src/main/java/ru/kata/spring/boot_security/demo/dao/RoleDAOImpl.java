package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getRole(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Set<Role> setRole(Long index) {
        Set<Role> rolesSet = new HashSet<>();
        if (index == 1) {
            rolesSet.add(getRole((long)1));
        } else if (index == 2) {
            rolesSet.add(getRole((long)2));
        } else if (index == 3) {
            rolesSet.add(getRole((long)1));
            rolesSet.add(getRole((long)2));
        }
        return rolesSet;
    }
}
