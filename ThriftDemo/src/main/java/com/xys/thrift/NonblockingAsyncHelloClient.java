package com.xys.thrift;

import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

public class NonblockingAsyncHelloClient {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8080;
    public static final int TIMEOUT = 30000;

    public void startClient(String userName) throws Exception {
        TAsyncClientManager clientManager = new TAsyncClientManager();
        TNonblockingTransport transport = new TNonblockingSocket(SERVER_IP,
                SERVER_PORT, TIMEOUT);

        // 协议要和服务端一致
        TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
        HelloWorldService.AsyncClient client = new HelloWorldService.AsyncClient(
                protocolFactory, clientManager, transport);

        client.sayHello(userName, new AsyncHandler());

        Thread.sleep(500);
    }

    class AsyncHandler implements AsyncMethodCallback<String> {
        @Override
        public void onComplete(String response) {
            System.out.println("Got result: " + response);
        }

        @Override
        public void onError(Exception exception) {
            System.out.println("Got error: " + exception.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        NonblockingAsyncHelloClient client = new NonblockingAsyncHelloClient();
        client.startClient("XYS");
    }
}