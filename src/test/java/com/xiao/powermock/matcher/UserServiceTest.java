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
    * ����ͬһ����������ͬ��������ͬһ��ֵ
    * */
    @Test
    public void testFind() throws Exception {
        //mock һ������newһ������������Ķ��󣬷��ظ�userDao
        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
        //��userDao����queryByName����������ͬһ��ֵ��
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
     * ģ�������ͬ������ֵ��ͬ��ʹ��Matchers��ʵ�֣�
     * */
    @Test
    public void testFindWithMatcher() throws Exception {
        //mock һ������newһ������������Ķ��󣬷��ظ�userDao
        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
        //��ѯ�ֶΣ�ʹ��Matchers�����岻ͬ��ֵ��������ͬһ��ֵ
        when(userDao.queryByName(Matchers.argThat(new MyArgumentMatcher()))).thenReturn("wangwenjun");
        UserService service = new UserService();

        assertEquals("wangwenjun", service.find("Alex"));
        assertEquals("wangwenjun", service.find("Jacky"));
        assertEquals("wangwenjun", service.find("Van"));
        assertEquals("wangwenjun", service.find("Tony"));


    }

    /**
     * mock ��ͬ�Ĳ������ز�ͬ��ֵ��ʹ��Answer��ʵ��
     * */
    @Test
    public void testFindWithAnswer() throws Exception {

        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
        //then����Ĵ�������lambda��ʽʵ�ֵģ�ʵ��Answer�ӿڵķ�����
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
        //���Է���ֵ��Ԥ�ڽ���Ƿ�һ��
        assertEquals("I am Jack.", service.find("Jack"));
        assertEquals("I am Alex.", service.find("Alex"));
        //���Է���ֵΪ����ֵ�����׳��쳣���˴����쳣���񣬲���֤�Ƿ������׳�
        try {
            String anyValue = service.find("AnyValue");
            fail("never process to here is right.");
        } catch (Exception e) {
            //��֤�Ƿ��׳��쳣���׳�RuntimeException���򷵻�true��
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
