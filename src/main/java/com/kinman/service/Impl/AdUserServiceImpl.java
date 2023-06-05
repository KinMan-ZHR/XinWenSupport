package com.kinman.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kinman.mapper.UsersMapper;
import com.kinman.pojo.PageBean;
import com.kinman.pojo.User;
import com.kinman.service.AdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdUserServiceImpl implements AdUserService {
    @Autowired
    private UsersMapper usersMapper;
    /**
     * @param id 用户id
     * @return 用户
     */
    @Override
    public int deleteUserById(long id) {
        usersMapper.deleteUserById(id);
        return 0;
    }

    /**
     * @param page    当前页
     * @param pageSize 每页显示的记录数
     * @param username 用户名
     * @param identity 身份
     * @return 用户列表
     */
    @Override
    public PageBean getUserList(Integer page, Integer pageSize, String username, Boolean identity) {

        PageHelper.startPage(page,pageSize);
        Page<User> userPage=(Page<User>) usersMapper.getUserList(username,identity);
        return new PageBean(userPage.getTotal(),userPage.getResult());
    }

    /**
     * @param user  用户
     * @return 用户列表
     */
    @Override
    public User login(User user) {
        return usersMapper.getUserByNameAndPassword(user);
    }

    /**
     * @param user 用户
     *             添加用户
     *
     */
    @Override
    public void addUser(User user) {
        usersMapper.addUser(user);

    }

    /**
     * @param user  用户
     *              更新用户
     */
    @Override
    public void updateUser(User user) {
        usersMapper.updateUser(user);

    }

    /**
     * @param id  用户id
     * @return 用户
     */
    @Override
    public User getUserById(long id) {

        return usersMapper.getUserById(id);
    }

    /**
     * @param id  用户id
     * @param status 用户状态
     */
    @Override
    public void updateUserStatus(long id, Boolean status) {
        usersMapper.updateUserStatus(id,status);

    }
}
