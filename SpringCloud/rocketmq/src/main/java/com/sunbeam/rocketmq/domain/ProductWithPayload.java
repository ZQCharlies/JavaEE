package com.sunbeam.rocketmq.domain;
import lombok.Data;

@Data
public class ProductWithPayload<T> {
    private String productName;
    private T payload;

    @Override public String toString() {
        return "ProductWithPayload{" +
            "productName='" + productName + '\'' +
            ", payload=" + payload +
            '}';
    }
}