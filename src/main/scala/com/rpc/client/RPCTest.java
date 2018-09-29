package com.rpc.client;

import com.rpc.service.HelloService;
import com.rpc.service.HelloServiceImpl;
import com.rpc.service.Server;
import com.rpc.service.ServiceCenter;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCTest {
    public static void main(String [] args) throws IOException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Server serviceServer = new ServiceCenter(8088);
                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
                    serviceServer.start();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();

        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class,
                new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("test"));
    }
}
