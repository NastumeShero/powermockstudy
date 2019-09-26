package com.xiao.powermock.simple;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/16 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class Test {

    public String hello(String name){
        InnerClass innerClass = new InnerClass();
        return innerClass.action(name);
    }


    private static class InnerClass{
        public String action(String name){
            throw new UnsupportedOperationException();
        }
    }
}


