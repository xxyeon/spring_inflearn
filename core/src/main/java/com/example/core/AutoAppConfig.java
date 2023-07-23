package com.example.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@ComponentScan: 스프링 빈을 다 끌어다가 자동으로 스프링 빈으로 끌어올리는..
@ComponentScan(
        //스프링 빈 등록에서 제외할 것
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)

public class AutoAppConfig {}