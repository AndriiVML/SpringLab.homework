package com.mlav.spring.core.homework1.injection;

import com.mlav.spring.core.homework1.other_beans.OtherBeanA;
import org.springframework.stereotype.Component;

/**
 * Constructor-based Dependency Injection
 */
@Component
public class InjectedOtherBeanA {
    private OtherBeanA otherBeanA;

    public InjectedOtherBeanA(OtherBeanA otherBeanA){
        this.otherBeanA = otherBeanA;
    }

    @Override
    public String toString() {
        return "InjectedOtherBeanA injects {" +
                "otherBeanA=" + otherBeanA +
                '}';
    }
}
