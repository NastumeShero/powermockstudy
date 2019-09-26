package com.xiao.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PowerMockClass.class)
public class PowerMockClassTest {

    //设定该测试方法的期望值为IllegalStateException；若没有设置期望值，则需要在代码中捕获异常;
    @Test //(expected = IllegalStateException.class)
    public void testVoid_static() throws Exception {
        mockStatic(PowerMockClass.class);
        doThrow(new IllegalStateException()).when(PowerMockClass.class, "void_static");
        try {
            PowerMockClass.void_static();
        } catch (IllegalStateException e) {
            System.out.println("This is illegalStateException");
        }
        verifyStatic(PowerMockClass.class);

    }

    @Test
    public void testStatic_method() {
        mockStatic(PowerMockClass.class);
        when(PowerMockClass.static_method()).thenReturn(5);
        assertEquals(5, PowerMockClass.static_method());
        verifyStatic(PowerMockClass.class);
    }

    @Test(expected = IllegalStateException.class)
    public void testStatic_arg() throws Exception {
        mockStatic(PowerMockClass.class);
        doThrow(new IllegalStateException()).when(PowerMockClass.class, "static_arg", 1);
        PowerMockClass.static_arg(1);
    }

    @Test
    public void testPrivate_method() throws Exception {
        PowerMockClass power = mock(PowerMockClass.class);
        when(power, "private_method", 2).thenReturn(3);
        Class<?> cls = Class.forName("com.xiao.powermock.PowerMockClass");
        Method method = cls.getDeclaredMethod("private_method", int.class);
        method.setAccessible(true);
        assertTrue(method.invoke(power, 2).equals(3));
    }

    @Test
    public void testFinal_method() throws Exception {
        PowerMockClass power = mock(PowerMockClass.class);
        when(power.final_method(1)).thenReturn(2);
        assertEquals(2, power.final_method(1));
    }

    //模拟方法内部中new出来的对象
    @Test(expected = NullPointerException.class)
    public void testNew_object() throws Exception {
        PowerMockClass power = new PowerMockClass();
        FileInputStream is = mock(FileInputStream.class);
        //new带任意参数的FileInputStream对象，均返回mock出来的is对象
        whenNew(FileInputStream.class).withAnyArguments().thenReturn(is);
        //mock is对象调用close()方法时，抛出NullPointerException
        doThrow(new NullPointerException()).when(is).close();
        power.new_object();
    }

    @Test
    public void testSpy() throws Exception {
        PowerMockClass power = spy(new PowerMockClass());
        when(power, "private_method", 1).thenReturn(5);

        Class<?> cls = Class.forName("com.xiao.powermock.PowerMockClass");
        Method method = cls.getDeclaredMethod("private_method", int.class);
        method.setAccessible(true);
        //执行的mock的方法的值，
        assertTrue(method.invoke(power, 1).equals(5));
        //执行的方法内部的代码逻辑
        try {
            power.void_static();
        } catch (NullPointerException e) {
            assertTrue("void_static".equals(e.getMessage()));
        }
        assertEquals(1, power.static_method());
        try {
            power.static_arg(1);
        } catch (NullPointerException e) {
        }
    }
}
