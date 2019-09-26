package com.xiao.powermock.mockstatic.service;

import com.xiao.powermock.common.User;
import com.xiao.powermock.mockstatic.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by wangwenjun on 2016/10/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class, UserDao.class})
public class UserServiceTest {

    @Test
    public void testQueryUserCount() throws Exception {
        //mock��̬�����
        mockStatic(UserDao.class);
        //mock getCount()����������10
        when(UserDao.getCount()).thenReturn(10);
        UserService userService = new UserService();
        int result = userService.queryUserCount();
        //����
        assertEquals(10, result);
    }

    @Test
    public void testSaveUser() throws Exception {
        mockStatic(UserDao.class);
        User user = new User();
        doNothing().when(UserDao.class);

        UserService userService = new UserService();
        userService.saveUser(user);

//        PowerMockito.verifyStatic();
        PowerMockito.verifyStatic(UserDao.class);

    }
}
