package com.xys.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;


public class NonblockingHelloServer {
    public static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws Exception {
        NonblockingHelloServer server = new NonblockingHelloServer();
        server.startServer();
    }

    public void startServer() throws Exception {
        TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(
                new HelloWorldImpl());

        TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(SERVER_PORT);
        TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
        tArgs.processor(tprocessor);
        tArgs.protocolFactory(new TBinaryProtocol.Factory());
//        tArgs.transportFactory(new TFastFramedTransport.Factory());

        TServer server = new TNonblockingServer(tArgs);
        server.serve();
    }
}