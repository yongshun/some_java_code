package com.xys.demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;

/**
 * @author xiongyongshun
 * @version 1.0
 * @created 2016/11/11 22:41
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class ExpiredAopDemo {
    @Autowired
    SomeService someService;

    public static void main(String[] args) {
        SpringApplication.run(ExpiredAopDemo.class, args);
    }

    @PostConstruct
    public void test() {
        someService.someMethod();
    }
}
