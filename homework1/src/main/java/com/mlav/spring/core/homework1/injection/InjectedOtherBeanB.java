package com.mlav.spring.core.homework1.injection;


import com.mlav.spring.core.homework1.other_beans.OtherBeanB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Setter-based Dependency Injection
 */
@Component
public class InjectedOtherBeanB {
    private OtherBeanB otherBeanB;
    @Autowired
    public void setOtherBeanB(OtherBeanB otherBeanB){
        this.otherBeanB = otherBeanB;
    }

    @Override
    public String toString() {
        return "InjectedOtherBeanB injects {" +
                "otherBeanB=" + otherBeanB +
                '}';
    }
}
