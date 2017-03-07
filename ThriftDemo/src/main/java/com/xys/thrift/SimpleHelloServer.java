package com.xys.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

public class SimpleHelloServer {
    public static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws Exception {
        SimpleHelloServer server = new SimpleHelloServer();
        server.startServer();
    }

    public void startServer() throws Exception {
        TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(
                new HelloWorldImpl());

        TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
        TSimpleServer.Args tArgs = new TSimpleServer.Args(serverTransport);
        tArgs.processor(tprocessor);
        tArgs.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new TSimpleServer(tArgs);

        server.serve();
    }
}