package com.xys;

import com.xys.mapper.AccountMapper;
import com.xys.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {
    Logger logger = LoggerFactory.getLogger("test");

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    AccountMapper accountMapper;

    @PostConstruct
    public void test() {
        Account account = accountMapper.select("a");
        logger.info("Name: {}, email: {}", account.getName(), account.getEmail());
    }
}