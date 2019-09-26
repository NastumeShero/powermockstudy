package com.xiao.powermock.local.service;

import com.xiao.powermock.common.User;
import com.xiao.powermock.local.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * Created by wangwenjun on 2016/10/8.
 */
//表明用 PowerMockerRunner来运行测试用例，否则无法使用PowerMock
@RunWith(PowerMockRunner.class)
//所有需要准备好的测试的类放置在@PrepareForTest里，多个类用逗号分开；实际是让程序来改变UserService.class的字节码；
@PrepareForTest({UserService.class})
public class UserServiceTest {

    /**
     * Mock局部变量
     * */
    @Test
    public void testQueryUserCount() {

        try {
            UserService userService = new UserService();
            //mock一个userDao的变量，如何将该变量放置到queryUserCount()方法中去；需要在测试类加入@PrepareForTest的注解
            UserDao userDao = mock(UserDao.class);
            System.out.println(userDao.getClass());
            //mock 新建一个不带任何参数的对象，返回userDao；
            whenNew(UserDao.class).withNoArguments().thenReturn(userDao);
            //Mock userDao.getCount()方法，并断言返回值为10
            doReturn(10).when(userDao).getCount();
            //调用queryUserCount()方法
            int result = userService.queryUserCount();
            //验证queryUserCount()方法的值与断言的是否一致，不一致则测试失败
            assertEquals(10, result);
        } catch (Throwable e) {
            fail();
        }
    }

    /**
     *  mock 新建变量，新建一个带任意参数的变量
     * */
    @Test
    public void testSaveUser() {

        try {
            User user = new User();
            UserService userService = new UserService();
            //mock一个userDao的对象；
            UserDao userDao = mock(UserDao.class);
            //通过mock，新建一个带任意参数的UserDao对象，均返回userDao
            whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
            //mock 一个无返回值的方法，什么也不做
            doNothing().when(userDao).insertUser(user);

            userService.saveUser(user);
            //验证userDao的insertUser(user)方法只被调用一次
            Mockito.verify(userDao, Mockito.times(1)).insertUser(user);


        } catch (Throwable e) {
            fail();
        }

    }
}

