package com.mlav.spring.core.homework2.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class BeanF implements Validator{
    private String name;
    private int value;

    @Override
    public String toString() {
        return "BeanF{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public void validate() {
        StringBuilder sb = new StringBuilder();
        if (name == null) {
            sb.append("Name is null. ");
        }
        if (value <= 0) {
            sb.append("Value is not positive. ");
        }
        if (sb.length() != 0) {
            System.out.println(" is invalid: " + sb);
            return;
        }
        System.out.println(" is valid.");
    }
}
