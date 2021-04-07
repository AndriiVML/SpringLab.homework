package com.mlav.spring.core.homework1.interface_beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BossBean {
    private Car mazda;
    private Car nissan;
    private Car volkswagen;

    @Autowired
    public BossBean(Car mazda, @Qualifier("nissan") Car nissan, @Qualifier("volkswagen") Car volkswagen){
        this.mazda = mazda;
        this.nissan = nissan;
        this.volkswagen = volkswagen;
    }

    @Override
    public String toString() {
        return "BossBean injects {" +
                "mazda=" + mazda.getCar() +
                ", nissan=" + nissan.getCar() +
                ", volkswagen=" + volkswagen.getCar() +
                '}';
    }
}
