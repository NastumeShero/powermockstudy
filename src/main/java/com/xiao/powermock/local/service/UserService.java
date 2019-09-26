package com.xiao.powermock.local.service;

import com.xiao.powermock.common.User;
import com.xiao.powermock.local.dao.UserDao;

/**
 * Created by wangwenjun on 2016/10/8.
 */
public class UserService {

    public int queryUserCount() {
        UserDao userDao = new UserDao();
        return userDao.getCount();
    }

    public void saveUser(User user) {
        UserDao userDao = new UserDao();
        userDao.insertUser(user);
    }
}
