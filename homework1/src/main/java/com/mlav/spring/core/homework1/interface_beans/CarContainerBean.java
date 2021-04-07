package com.mlav.spring.core.homework1.interface_beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarContainerBean {
    private final List<Car> cars;

    @Autowired
    public CarContainerBean(List<Car>cars){
        this.cars = cars;
    }

    public void printCars(){
        for(Car car:cars){
            System.out.println(car.getCar());
        }
    }
}
