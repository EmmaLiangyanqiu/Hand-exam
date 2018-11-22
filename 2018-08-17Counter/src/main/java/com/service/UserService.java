package com.service;

import com.entity.User;

import java.util.List;

/**
 * Created by Emma on 2018/8/21.
 */
public interface UserService {
    public List<User> queryAll();
    public User findByid(int id);
    public User findByname(String username);
    public void addUser(User user);
    public void deleteUser(int id);
    public void updateUser(User user);
}
