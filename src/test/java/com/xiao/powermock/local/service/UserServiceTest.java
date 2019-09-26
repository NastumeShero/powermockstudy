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
//������ PowerMockerRunner�����в��������������޷�ʹ��PowerMock
@RunWith(PowerMockRunner.class)
//������Ҫ׼���õĲ��Ե��������@PrepareForTest�������ö��ŷֿ���ʵ�����ó������ı�UserService.class���ֽ��룻
@PrepareForTest({UserService.class})
public class UserServiceTest {

    /**
     * Mock�ֲ�����
     * */
    @Test
    public void testQueryUserCount() {

        try {
            UserService userService = new UserService();
            //mockһ��userDao�ı�������ν��ñ������õ�queryUserCount()������ȥ����Ҫ�ڲ��������@PrepareForTest��ע��
            UserDao userDao = mock(UserDao.class);
            System.out.println(userDao.getClass());
            //mock �½�һ�������κβ����Ķ��󣬷���userDao��
            whenNew(UserDao.class).withNoArguments().thenReturn(userDao);
            //Mock userDao.getCount()�����������Է���ֵΪ10
            doReturn(10).when(userDao).getCount();
            //����queryUserCount()����
            int result = userService.queryUserCount();
            //��֤queryUserCount()������ֵ����Ե��Ƿ�һ�£���һ�������ʧ��
            assertEquals(10, result);
        } catch (Throwable e) {
            fail();
        }
    }

    /**
     *  mock �½��������½�һ������������ı���
     * */
    @Test
    public void testSaveUser() {

        try {
            User user = new User();
            UserService userService = new UserService();
            //mockһ��userDao�Ķ���
            UserDao userDao = mock(UserDao.class);
            //ͨ��mock���½�һ�������������UserDao���󣬾�����userDao
            whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
            //mock һ���޷���ֵ�ķ�����ʲôҲ����
            doNothing().when(userDao).insertUser(user);

            userService.saveUser(user);
            //��֤userDao��insertUser(user)����ֻ������һ��
            Mockito.verify(userDao, Mockito.times(1)).insertUser(user);


        } catch (Throwable e) {
            fail();
        }

    }
}

