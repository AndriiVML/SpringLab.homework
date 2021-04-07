package com.mlav.spring.core.homework2.config;

import com.mlav.spring.core.homework2.beans.BeanB;
import com.mlav.spring.core.homework2.beans.BeanC;
import com.mlav.spring.core.homework2.beans.BeanD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@Import(AppConfigSecondary.class)
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Autowired
    private Environment env;

    @Bean(value = "beanB", initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanD")
    public BeanB getBeanB() {
        return new BeanB(env.getProperty("beanB.name"), Integer.parseInt(env.getProperty("beanB.value")));
    }

    @Bean(value = "beanC", initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanB")
    public BeanC getBeanC() {
        return new BeanC(env.getProperty("beanC.name"), Integer.parseInt(env.getProperty("beanC.value")));
    }

    @Bean(value = "beanD", initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public BeanD getBeanD() {
        return new BeanD(env.getProperty("beanD.name"), Integer.parseInt(env.getProperty("beanD.value")));
    }

}
