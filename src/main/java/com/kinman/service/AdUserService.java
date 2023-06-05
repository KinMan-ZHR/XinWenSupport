package com.kinman.service;

import com.kinman.pojo.PageBean;
import com.kinman.pojo.User;

public interface AdUserService {
    int deleteUserById(long id);

    PageBean getUserList(Integer page, Integer pageSize, String username, Boolean identity);

    User login(User user);

    void addUser(User user);

    void updateUser(User user);

    User getUserById(long id);

    void updateUserStatus(long id, Boolean status);
}
