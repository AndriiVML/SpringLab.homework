package com.mlav.spring.core.homework1.interface_beans;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Nissan implements Car{
    @Override
    public String getCar() {
        return "Nissan Qashkai";
    }
}
