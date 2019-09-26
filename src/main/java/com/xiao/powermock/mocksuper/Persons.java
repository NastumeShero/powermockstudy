package com.xiao.powermock.mocksuper;

/**
 * @author wuhao1
 */
public class Persons {
//    private String name;
//    private int age;
//    private int count;


//    public Persons(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }

    int count(int count) {
        System.out.println("This is the count method of super");
        return count;
    }

    String add(String name){
        System.out.println("This is the add method of super");
        return name;
    }

    void remove(String name) throws Exception {
        throw new Exception();
    }


//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
}
