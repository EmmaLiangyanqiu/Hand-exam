package com.service;

import com.entity.User;
import com.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Emma on 2018/8/18.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> queryAll(){
        List<User> userList=userMapper.queryAll();
        return userList;
    }

    @Override
    public User findByid(int id) {
      User user= userMapper.findByid(id);
        return user;
    }

    @Override
    public User findByname(String username) {
       User user= userMapper.findByname(username);
        return user;
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }


    @Override
    public void deleteUser(int id) {
      userMapper.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
