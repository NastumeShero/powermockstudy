package com.xiao.powermock.simple;

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/16 QQ:532500648
 * QQ交流群:286081824
 ***************************************/

@RunWith(PowerMockRunner.class)
@PrepareForTest(Test.class)
public class TestTest {

    @org.junit.Test
    public void testHello() throws Exception {
        Test test = PowerMockito.mock(Test.class);
        PowerMockito.whenNew(Test.class).withAnyArguments().thenReturn(test);
        PowerMockito.when(test.hello("Alex")).thenReturn("Hello Alex");
        Test testInstance = new Test();
        String alex = testInstance.hello("Alex");
        assertEquals("Hello Alex",alex);
    }
}
