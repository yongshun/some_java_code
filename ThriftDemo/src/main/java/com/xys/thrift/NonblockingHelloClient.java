package com.xys.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class NonblockingHelloClient {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8080;
    public static final int TIMEOUT = 30000;

    public void startClient(String userName) throws Exception {
        TTransport transport = null;

        transport = new TFastFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
        // 协议要和服务端一致
        TProtocol protocol = new TBinaryProtocol(transport);
        HelloWorldService.Client client = new HelloWorldService.Client(
                protocol);
        transport.open();
        String result = client.sayHello(userName);
        System.out.println("Result: " + result);

        transport.close();
    }

    public static void main(String[] args) throws Exception {
        NonblockingHelloClient client = new NonblockingHelloClient();
        client.startClient("XYS");
    }
}