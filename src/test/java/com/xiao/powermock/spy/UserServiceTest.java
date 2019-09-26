package com.xiao.powermock.spy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by wangwenjun on 2016/10/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class UserServiceTest {

    /**
     * userService.foo(arg)���������I am console log.
     * */
    @Test
    public void testFoo() throws Exception {
        //mock һ��ʵ�����Ķ���,�����õķ�����mock����ʹ��mock�����ݣ������õ�����û�б�mock����ִ�д���ʵ�塣
        UserService userService = PowerMockito.spy(new UserService());
      ;
        String arg = "hello";
        //mock �޷���ֵ��foo����ʱ��ʲô������
        PowerMockito.doNothing().when(userService).foo(arg);
        //������Ϊhelloʱ��ʹ��mock�����ݣ��������ӡlog()�����е�����
        userService.foo(arg);
        //������Ϊworldʱ����ִ��foo()�����е�ʵ�壬��ִ��log()�������ڿ���̨��ӡI am console log.
        userService.foo("world");
    }

    @Test
    public void testCheck() throws Exception {
        //mock һ��ʵ������
        UserService userService = PowerMockito.spy(new UserService());
        //mock userService��checkExist����������Ϊalex��������true��
        PowerMockito.doReturn(true).when(userService, "checkExist", "alex");
        //���÷���ʱ��ʹ��mock������
        assertTrue(userService.exist("alex"));
        //ִ��exist�����е�ʵ�����
        userService.exist("other");

    }
}
