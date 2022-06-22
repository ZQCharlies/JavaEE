package com.sunbeam.rocketmq.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private String userName;
    private Byte userAge;

    @Override
    public String toString() {
        return "User{" +
            "userName='" + userName + '\'' +
            ", userAge=" + userAge +
            '}';
    }
}