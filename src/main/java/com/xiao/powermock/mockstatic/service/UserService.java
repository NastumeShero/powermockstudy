package com.xiao.powermock.mockstatic.service;

import com.xiao.powermock.common.User;
import com.xiao.powermock.mockstatic.dao.UserDao;

/**
 * Created by wangwenjun on 2016/10/8.
 */
public class UserService {

    public int queryUserCount() {
        //getCount()是静态方法
        return UserDao.getCount();
    }

    public void saveUser(User user) {
        //insertUser(user)是静态方法
        UserDao.insertUser(user);
    }
}
