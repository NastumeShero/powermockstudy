package com.xiao.powermock.helloworld.service;

import com.xiao.powermock.common.User;
import com.xiao.powermock.helloworld.dao.UserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.*;

/**
 * Created by wangwenjun on 2016/10/8.
 */
public class UserServiceTest {

    private UserService userService;

    @Before
    public void setup() {

        userService = new UserService(new UserDao());
    }

    @Mock
    private UserDao userDao;

    /**
     * do...when...then
     * when...()...then
     * 通过PowerMock来测试queryUserCount()方法
     */
    @Test
    public void testQueryUserCountWithPowerMock() {
        //mock一个UserDao类的对象
        UserDao uDao = PowerMockito.mock(UserDao.class);

        //        PowerMockito.doReturn(10).when(uDao.getCount()); 错误的写法，会报异常，提示该语句缺少thenReturn()方法；

        /**
         * Mock public方法，此处为mock uDao.getCount()方法，并返回10
         *  等同于 PowerMockito.doReturn(10).when(uDao).getCount();
         */
        PowerMockito.when(uDao.getCount()).thenReturn(10);
        //构造service对象
        UserService service = new UserService(uDao);
        //调用要测试的方法
        int result = service.queryUserCount();
        assertEquals(10, result);
    }

    /**
     * 验证public方法是否被调用
     * */
    @Test
    public void testSaveUserWithPowerMock() {
        //mock 一个userDao的对象
        UserDao uDao = PowerMockito.mock(UserDao.class);
        User user = new User();
        //mock返回值为void的insertUser(user)方法，
        PowerMockito.doNothing().when(uDao).insertUser(user);
        UserService userService = new UserService(uDao);
        //调用saveUser(user)方法，该方法调用了insertUser(user)方法；
        userService.saveUser(user);
        //验证uDao.insertUser(user)方法是否被调用，调用了则测试通过，没有调用则失败
        Mockito.verify(uDao).insertUser(user);
    }

    /**
     * 通过Mockito方法来测试queryUserCount()方法
     * */
    @Ignore
    @Test
    public void testQueryUserCountWithMockito() {
        //初始化Mock
        MockitoAnnotations.initMocks(this);

        UserService service = new UserService(userDao);
        //Mock userDao.getCount()方法，返回值为10
        Mockito.when(userDao.getCount()).thenReturn(10);
        //调用queryUserCount()方法
        int result = service.queryUserCount();
        //断言调用queryUserCount()方法后的值是否为10
        assertEquals(10, result);
    }

    /**
     * 通过Junit来测试queryUserCount()方法
     * */
    @Ignore
    @Test
    public void testQueryUserCountWithJunit() {

        try {
            userService.queryUserCount();
            fail("should not process to here.");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    @Ignore
    @Test
    public void testSaveUserWithJunit() {
        try {
            userService.saveUser(new User());
            fail("should not process to here.");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }
}
