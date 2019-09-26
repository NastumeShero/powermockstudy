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
     * ͨ��PowerMock������queryUserCount()����
     */
    @Test
    public void testQueryUserCountWithPowerMock() {
        //mockһ��UserDao��Ķ���
        UserDao uDao = PowerMockito.mock(UserDao.class);

        //        PowerMockito.doReturn(10).when(uDao.getCount()); �����д�����ᱨ�쳣����ʾ�����ȱ��thenReturn()������

        /**
         * Mock public�������˴�Ϊmock uDao.getCount()������������10
         *  ��ͬ�� PowerMockito.doReturn(10).when(uDao).getCount();
         */
        PowerMockito.when(uDao.getCount()).thenReturn(10);
        //����service����
        UserService service = new UserService(uDao);
        //����Ҫ���Եķ���
        int result = service.queryUserCount();
        assertEquals(10, result);
    }

    /**
     * ��֤public�����Ƿ񱻵���
     * */
    @Test
    public void testSaveUserWithPowerMock() {
        //mock һ��userDao�Ķ���
        UserDao uDao = PowerMockito.mock(UserDao.class);
        User user = new User();
        //mock����ֵΪvoid��insertUser(user)������
        PowerMockito.doNothing().when(uDao).insertUser(user);
        UserService userService = new UserService(uDao);
        //����saveUser(user)�������÷���������insertUser(user)������
        userService.saveUser(user);
        //��֤uDao.insertUser(user)�����Ƿ񱻵��ã������������ͨ����û�е�����ʧ��
        Mockito.verify(uDao).insertUser(user);
    }

    /**
     * ͨ��Mockito����������queryUserCount()����
     * */
    @Ignore
    @Test
    public void testQueryUserCountWithMockito() {
        //��ʼ��Mock
        MockitoAnnotations.initMocks(this);

        UserService service = new UserService(userDao);
        //Mock userDao.getCount()����������ֵΪ10
        Mockito.when(userDao.getCount()).thenReturn(10);
        //����queryUserCount()����
        int result = service.queryUserCount();
        //���Ե���queryUserCount()�������ֵ�Ƿ�Ϊ10
        assertEquals(10, result);
    }

    /**
     * ͨ��Junit������queryUserCount()����
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
