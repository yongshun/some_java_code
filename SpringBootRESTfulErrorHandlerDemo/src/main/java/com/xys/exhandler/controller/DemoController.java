package com.xys.exhandler.controller;

import com.xys.exhandler.exception.BaseException;
import com.xys.exhandler.exception.MyException1;
import com.xys.exhandler.exception.MyException2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author xiongyongshun
 * @version 1.0
 * @created 16/8/29 13:04
 */
@RestController
public class DemoController {
    private Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler");

    @RequestMapping("/ex1")
    public Object throwBaseException() throws Exception {
        throw new BaseException("This is BaseException.");
    }

    @RequestMapping("/ex2")
    public Object throwMyException1() throws Exception {
        throw new MyException1("This is MyException1.");
    }

    @RequestMapping("/ex3")
    public Object throwMyException2() throws Exception {
        throw new MyException2("This is MyException1.");
    }

    @RequestMapping("/ex4")
    public Object throwIOException() throws Exception {
        throw new IOException("This is IOException.");
    }

    @RequestMapping("/ex5")
    public Object throwNullPointerException() throws Exception {
        throw new NullPointerException("This is NullPointerException.");
    }

    @ExceptionHandler(NullPointerException.class)
    public String controllerExceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("---ControllerException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return "---ControllerException Handler---:" + e.getMessage();
    }
}