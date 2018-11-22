package com.mappers;


import com.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Emma on 2018/8/18.
 */
@Component
public interface UserMapper {

    public User findByid(int id);

    public List<User> queryAll();

    public User findByname(String username);

    public void addUser(User user);

    public void deleteUser(int id);

    public void updateUser(User user);

    public String getRole(int id);

}
