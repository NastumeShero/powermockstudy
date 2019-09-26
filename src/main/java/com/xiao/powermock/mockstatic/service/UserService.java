package com.xiao.powermock.mockstatic.service;

import com.xiao.powermock.common.User;
import com.xiao.powermock.mockstatic.dao.UserDao;

/**
 * Created by wangwenjun on 2016/10/8.
 */
public class UserService {

    public int queryUserCount() {
        //getCount()�Ǿ�̬����
        return UserDao.getCount();
    }

    public void saveUser(User user) {
        //insertUser(user)�Ǿ�̬����
        UserDao.insertUser(user);
    }
}
