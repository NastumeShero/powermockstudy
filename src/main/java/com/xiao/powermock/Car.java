package com.xiao.powermock;

import java.util.Optional;

public class Car {

    private Insurance insurance;

    public Optional<Insurance> getInsurance(){
        return Optional.ofNullable(insurance);
    }
}
