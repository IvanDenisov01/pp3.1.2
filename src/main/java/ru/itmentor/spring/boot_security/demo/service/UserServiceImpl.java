package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.domain.repository.UserDao;
import ru.itmentor.spring.boot_security.demo.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public void addUser(User user) {
        userDao.createUser(user);
    }

    @Transactional
    public void editUser(int id, User updatedUser) {
        updatedUser.setId(id);
        userDao.updateUser(updatedUser);
    }

    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void updateUser(int id, User updatedUser) {
        updatedUser.setId(id);
        userDao.updateUser(updatedUser);
    }

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }
}