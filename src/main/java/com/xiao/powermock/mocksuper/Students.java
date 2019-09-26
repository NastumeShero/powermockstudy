package com.xiao.powermock.mocksuper;

/**
 * @author wuhao1
 */
public class Students extends Persons {

//    public Students(String name, int age) {
//        super(name, age);
//    }

    @Override
    String add(String name) {
        System.out.println("This is the add method of students");
        return super.add(name);
    }

    @Override
    void remove(String name) throws Exception {
        super.remove(name);
    }

    @Override
    int count(int count) {
        System.out.println("This is the count method of students");
        return super.count(count);
    }


}
