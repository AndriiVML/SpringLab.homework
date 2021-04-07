package com.mlav.spring.core.homework1.injection;


import com.mlav.spring.core.homework1.other_beans.OtherBeanC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Setter-based Dependency Injection
 */
@Component
public class InjectedOtherBeanC {
    @Qualifier("otherBeanC")
    @Autowired
    private OtherBeanC otherBeanC;

    @Override
    public String toString() {
        return "InjectedOtherBeanC injects {" +
                "otherBeanC=" + otherBeanC +
                '}';
    }
}
