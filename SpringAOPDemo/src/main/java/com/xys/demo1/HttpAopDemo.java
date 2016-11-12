package com.xys.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author xiongyongshun
 * @version 1.0
 * @created 2016/11/11 22:41
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class HttpAopDemo {

    public static void main(String[] args) {
        SpringApplication.run(HttpAopDemo.class, args);
    }
}
