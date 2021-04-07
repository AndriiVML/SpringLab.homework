package com.mlav.spring.core.homework1.interface_beans;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Primary
@Component
@Order(1)
public class Mazda implements Car {
    @Override
    public String getCar() {
        return "Mazda CX-5";
    }
}
