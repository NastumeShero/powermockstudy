package com.xiao.powermock.matcher;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by wangwenjun on 2016/10/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class UserServiceTest {
    /*
    * 测试同一个方法，不同参数返回同一个值
    * */
    @Test
    public void testFind() throws Exception {
        //mock 一个对象，new一个带任意参数的对象，返回给userDao
        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
        //当userDao调用queryByName方法，返回同一个值；
        when(userDao.queryByName("alex")).thenReturn("wangwenjun");
        UserService service = new UserService();
        String result = service.find("alex");
        assertEquals("wangwenjun", result);

        when(userDao.queryByName("Jacky")).thenReturn("wangwenjun");
        String jacky = service.find("Jacky");
        assertEquals("wangwenjun", jacky);

        when(userDao.queryByName("Tommy")).thenReturn("wangwenjun");
        String tommy = service.find("Tommy");
        assertEquals("wangwenjun", tommy);

    }

    /**
     * 模拟参数不同，返回值相同；使用Matchers来实现；
     * */
    @Test
    public void testFindWithMatcher() throws Exception {
        //mock 一个对象，new一个带任意参数的对象，返回给userDao
        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
        //查询字段，使用Matchers来定义不同的值；均返回同一个值
        when(userDao.queryByName(Matchers.argThat(new MyArgumentMatcher()))).thenReturn("wangwenjun");
        UserService service = new UserService();

        assertEquals("wangwenjun", service.find("Alex"));
        assertEquals("wangwenjun", service.find("Jacky"));
        assertEquals("wangwenjun", service.find("Van"));
        assertEquals("wangwenjun", service.find("Tony"));


    }

    /**
     * mock 不同的参数返回不同的值；使用Answer来实现
     * */
    @Test
    public void testFindWithAnswer() throws Exception {

        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
        //then里面的代码是用lambda方式实现的，实现Answer接口的方法；
        when(userDao.queryByName(Mockito.anyString())).then(invocationOnMock -> {
            String arg = (String) invocationOnMock.getArguments()[0];
            switch (arg) {
                case "Jack":
                    return "I am Jack.";
                case "Alex":
                    return "I am Alex.";
                default:
                    throw new RuntimeException("Not support " + arg);
        }});

        UserService service = new UserService();
        //断言返回值与预期结果是否一致
        assertEquals("I am Jack.", service.find("Jack"));
        assertEquals("I am Alex.", service.find("Alex"));
        //断言返回值为其他值，则抛出异常；此处将异常捕获，并验证是否正常抛出
        try {
            String anyValue = service.find("AnyValue");
            fail("never process to here is right.");
        } catch (Exception e) {
            //验证是否抛出异常，抛出RuntimeException，则返回true；
            assertTrue(e instanceof RuntimeException);
        }
    }




    static class MyArgumentMatcher extends ArgumentMatcher<String> {

        @Override
        public boolean matches(Object o) {
            String arg = (String) o;
            switch (arg) {
                case "Alex":
                case "Jacky":
                case "Van":
                case "Tony":
                    return true;
                default:
                    return false;
            }
        }
    }


    }
