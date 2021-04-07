package com.mlav.spring.core.homework1.conf;

import com.mlav.spring.core.homework1.beans3.BeanD;
import com.mlav.spring.core.homework1.beans3.BeanF;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans(
        {
                @ComponentScan("com.mlav.spring.core.homework1.beans1"),
                @ComponentScan("com.mlav.spring.core.homework1.beans2"),
                //instead of 2 @ComponentScan below
                /*
                 @ComponentScan(basePackages = {"beans.beans3"}, excludeFilters = {
                 @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = BeanE.class)})
                */
                @ComponentScan(basePackageClasses = BeanD.class),
                @ComponentScan(basePackageClasses = BeanF.class)
        }
)
public class AppConfig2 {

}
