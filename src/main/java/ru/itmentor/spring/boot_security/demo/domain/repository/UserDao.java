package ru.itmentor.spring.boot_security.demo.domain.repository;



import ru.itmentor.spring.boot_security.demo.domain.model.User;

import java.util.List;

public interface UserDao {

    void createUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUserById(int id);

    User getUserById(int id);

    User findByUsername(String username);
}
