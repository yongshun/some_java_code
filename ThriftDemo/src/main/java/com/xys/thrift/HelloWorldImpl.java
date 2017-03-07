package com.xys.thrift;

import org.apache.thrift.TException;

public class HelloWorldImpl implements HelloWorldService.Iface {
    public HelloWorldImpl() {
    }

    @Override
    public String sayHello(String name) throws TException {
        return "Hello, " + name;
    }
}