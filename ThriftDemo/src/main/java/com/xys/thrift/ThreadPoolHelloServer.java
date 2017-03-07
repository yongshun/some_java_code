package com.xys.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;



public class ThreadPoolHelloServer {
    public static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws Exception {
        ThreadPoolHelloServer server = new ThreadPoolHelloServer();
        server.startServer();
    }

    public void startServer() throws Exception {
        TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(
                new HelloWorldImpl());

        TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
        TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
        tArgs.processor(tprocessor);
        tArgs.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new TThreadPoolServer(tArgs);
        server.serve();
    }
}