package com.xiao.powermock.helloworld.service;

import com.xiao.powermock.common.User;
import com.xiao.powermock.helloworld.dao.UserDao;

/**
 * Created by wangwenjun on 2016/10/8.
 */
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {

        this.userDao = userDao;
    }

    public int queryUserCount() {
        return userDao.getCount();
    }

    public void saveUser(User user) {
        userDao.insertUser(user);
    }
}
