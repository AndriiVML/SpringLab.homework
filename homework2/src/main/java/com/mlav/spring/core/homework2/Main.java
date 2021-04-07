package com.mlav.spring.core.homework2;

import com.mlav.spring.core.homework2.beans.*;
import com.mlav.spring.core.homework2.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        AnotherBean anotherBean = ctx.getBean(AnotherBean.class);
        System.out.println(anotherBean);

        BeanA beanA = ctx.getBean(BeanA.class);
        System.out.println(beanA);
        BeanB beanB = ctx.getBean(BeanB.class);
        System.out.println(beanB);
        BeanC beanC = ctx.getBean(BeanC.class);
        System.out.println(beanC);
        BeanD beanD = ctx.getBean(BeanD.class);
        System.out.println(beanD);
        BeanE beanE = ctx.getBean(BeanE.class);
        System.out.println(beanE);
        BeanF beanF = ctx.getBean(BeanF.class);
        System.out.println(beanF);

        ctx.close();

    }
}
