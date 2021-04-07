package com.mlav.spring.core.homework1.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.mlav.spring.core.homework1.other_beans")
@ComponentScan("com.mlav.spring.core.homework1.injection")
public class AppConfigForOthers {
}
