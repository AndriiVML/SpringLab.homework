package com.mlav.spring.core.homework1.interface_beans;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class Volkswagen implements Car{
    @Override
    public String getCar() {
        return "Volkswagen Golf IV";
    }
}
