package com.xiao.powermock;

import java.util.Optional;

public class OptionalInAction {
    public static void main(String[] args) {
        Optional.ofNullable(getInsuranceNameByOptional(new Person())).ifPresent(System.out::println);

    }

    private static String getInsuranceNameByOptional(Person person) {

        return Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");


    }
}

