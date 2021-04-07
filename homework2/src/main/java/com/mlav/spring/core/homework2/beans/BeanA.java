package com.mlav.spring.core.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanA implements InitializingBean, DisposableBean, Validator {
    private String name;
    private int value;

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("beanA: inside DisposableBean.destroy()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("beanA: inside InitializingBean.afterPropertiesSet()");
    }

    @Override
    public void validate() {
        StringBuilder sb = new StringBuilder();
        if (name==null){
            sb.append("Name is null. ");
        }
        if(value<=0){
            sb.append("Value is not positive. ");
        }
        if(sb.length()!=0){
            System.out.println(" is invalid: "+ sb);
            return;
        }
        System.out.println(" is valid.");
    }
}
