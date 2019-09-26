package com.xiao.powermock.answer;


import com.xiao.powermock.matcher.UserDao;
import com.xiao.powermock.matcher.UserService;
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

    /**
     * Answer用法
     * mock 不同的参数返回不同的值
     */
    @Test
    public void testFindWithAnswer() throws Exception {

        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
        //此处的new myAnswer()方法可以用lambda表达式；
        when(userDao.queryByName(Mockito.anyString())).then(new myAnswer());

        UserService service = new UserService();

        assertEquals("I am Jacky.", service.find("Jacky"));
        assertEquals("I am Alex.", service.find("Alex"));

        try {
            String anyValue = service.find("AnyValue");
            fail("never process to here is right.");
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
    }




    class myAnswer implements Answer<String> {
        @Override
        public String answer(InvocationOnMock invocationOnMock) throws Throwable {

            String arg = (String) invocationOnMock.getArguments()[0];
            switch (arg) {
                case "Jacky":
                    return "I am Jacky.";
                case "Alex":
                    return "I am Alex.";
                default:
                    throw new RuntimeException("Not support " + arg);

            }
        }
    }

}
