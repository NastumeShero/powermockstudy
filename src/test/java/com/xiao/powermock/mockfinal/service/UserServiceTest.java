package com.xiao.powermock.mockfinal.service;

import com.xiao.powermock.mockfinal.dao.UserDao;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Created by wangwenjun on 2016/10/8.
 */
@RunWith(PowerMockRunner.class)
//UserDao为final类，故此处要加入UserDao类
@PrepareForTest({UserService.class,UserDao.class})
public class UserServiceTest {
/*  //使用注解的方式
    @Mock
    private UserDao userDao;

    @Ignore
    @Test
    public void testQueryUserCountWithMockito() throws Exception {
        MockitoAnnotations.initMocks(this);
        UserService userService = new UserService(userDao);
        Mockito.when(userDao.getCount()).thenReturn(10);
        int result = userService.queryUserCount();
        assertEquals(10, result);
    }*/


    @Test
    public void testQueryUserCountWithPowerMock() throws Exception {

        UserDao uDao = mock(UserDao.class);
        System.out.println(uDao.getClass());
        PowerMockito.when(uDao.getCount()).thenReturn(10);
        UserService userService = new UserService(uDao);
        int result = userService.queryUserCount();
        assertEquals(10, result);

    }

}
