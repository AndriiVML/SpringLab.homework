package com.mlav.spring.core.homework2.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + ": inside BeanPostProcessor.postProcessBeforeInitialization");
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + ": inside BeanPostProcessor.postProcessAfterInitialization    {");
        if (bean instanceof Validator) {
            System.out.print(beanName);
            ((Validator) bean).validate();
        }
        System.out.println("}");
        return bean;
    }

}
