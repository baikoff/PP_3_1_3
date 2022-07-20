package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDAO {
     List<Role> allRoles();

     Role getRole(Long id);

     Set<Role> setRole(Long index);
}