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
     * userService.foo(arg)方法均输出I am console log.
     * */
    @Test
    public void testFoo() throws Exception {
        //mock 一个实例化的对象,当调用的方法被mock，则使用mock的数据，若调用的内容没有被mock，则执行代码实体。
        UserService userService = PowerMockito.spy(new UserService());
      ;
        String arg = "hello";
        //mock 无返回值的foo方法时，什么都不做
        PowerMockito.doNothing().when(userService).foo(arg);
        //当参数为hello时，使用mock的数据，即不会打印log()方法中的内容
        userService.foo(arg);
        //当参数为world时，则执行foo()方法中的实体，即执行log()方法，在控制台打印I am console log.
        userService.foo("world");
    }

    @Test
    public void testCheck() throws Exception {
        //mock 一个实例对象
        UserService userService = PowerMockito.spy(new UserService());
        //mock userService的checkExist方法，参数为alex，并返回true；
        PowerMockito.doReturn(true).when(userService, "checkExist", "alex");
        //调用方法时，使用mock的数据
        assertTrue(userService.exist("alex"));
        //执行exist方法中的实体代码
        userService.exist("other");

    }
}
