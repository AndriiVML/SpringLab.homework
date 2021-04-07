package com.mlav.spring.core.homework1;

import com.mlav.spring.core.homework1.conf.AppConfig1;
import com.mlav.spring.core.homework1.conf.AppConfig2;
import com.mlav.spring.core.homework1.conf.AppConfigForOthers;
import com.mlav.spring.core.homework1.conf.AppConfigInterfaceBean;
import com.mlav.spring.core.homework1.injection.InjectedOtherBeanA;
import com.mlav.spring.core.homework1.injection.InjectedOtherBeanB;
import com.mlav.spring.core.homework1.injection.InjectedOtherBeanC;
import com.mlav.spring.core.homework1.interface_beans.BossBean;
import com.mlav.spring.core.homework1.interface_beans.Car;
import com.mlav.spring.core.homework1.interface_beans.CarContainerBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        final String mySeparator = "\n------------------------------------------------------------------------------\n";

        System.out.println("All beans using configuration class AppConfig1\n");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig1.class);
        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println(mySeparator);

        System.out.println("All beans using configuration class AppConfig2\n");
        ctx = new AnnotationConfigApplicationContext(AppConfig2.class);
        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println(mySeparator);

        System.out.println("Beans inject other beans\n");
        ctx = new AnnotationConfigApplicationContext(AppConfigForOthers.class);
        InjectedOtherBeanA injectedOtherBeanA = ctx.getBean(InjectedOtherBeanA.class);
        System.out.println(injectedOtherBeanA);
        InjectedOtherBeanB injectedOtherBeanB = ctx.getBean(InjectedOtherBeanB.class);
        System.out.println(injectedOtherBeanB);
        InjectedOtherBeanC injectedOtherBeanC = ctx.getBean(InjectedOtherBeanC.class);
        System.out.println(injectedOtherBeanC);
        System.out.println(mySeparator);

        System.out.println("Bean injects list of beans, output list using @Order\n");
        ctx = new AnnotationConfigApplicationContext(AppConfigInterfaceBean.class);
        CarContainerBean cars = ctx.getBean(CarContainerBean.class);
        cars.printCars();
        System.out.println(mySeparator);

        System.out.println("Bean injects other beans through common interface\n");
        BossBean bossBean = ctx.getBean(BossBean.class);
        System.out.println(bossBean);
        System.out.println(mySeparator);

        System.out.println("Bean with annotation @Primary\n");
        Car car = ctx.getBean(Car.class);
        System.out.println(car.getCar());

    }

}
