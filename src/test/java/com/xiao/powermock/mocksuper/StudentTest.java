package com.xiao.powermock.mocksuper;

import com.xiao.powermock.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StudentTest {

    @Test
    public void countTest() {
        Persons perMock=mock(Persons.class);
        Persons perSpy=spy(Persons.class);
        Persons stuMock =mock(Students.class);
        Persons stuSpy=spy(Students.class);

        Students stu1= new Students();

        doReturn(1).when(stuSpy).count(10);

        try {
            doNothing().doThrow(new Exception()).when(stuSpy).remove("xiao");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通過mock的對象均不會調用實際的方法
//        stuMock.count(1);
//        perMock.count(10);
        //通過spy的對象在沒有mock方法的情況下，會調用實際的方法
//        perSpy.count(10);
//        stuSpy.count(10);
        try {
            stuSpy.remove("xiao");
        } catch (Exception e) {
            System.out.println("ex");
            e.printStackTrace();
        }
        try {
            stuSpy.remove("xiao");
        } catch (Exception e) {
            System.out.println("the second");
            e.printStackTrace();
        }


    }
}
