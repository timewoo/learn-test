package com.learntest.websocket;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.net.InetSocketAddress;

/**
 * @author yanglin
 * @date 2021/3/1 21:01
 */
@Slf4j
public class MyWebsocketServer extends WebSocketServer {

    public MyWebsocketServer(InetSocketAddress inetSocketAddress){
        super(inetSocketAddress);
    }


    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        webSocket.send("Welcome to the server!");
        log.info("new connection: "+clientHandshake.getResourceDescriptor());
        log.info("new connection to: "+webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        log.info("closed " + webSocket.getRemoteSocketAddress()+"with exit code "+i+"additional info: "+s);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        log.info("received message from "+webSocket.getRemoteSocketAddress()+":"+s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        log.info("an error occurred on connection "+webSocket.getRemoteSocketAddress()+":"+e);
    }

    @Override
    public void onStart() {
        log.info("server started successfully");
    }

    public static void main(String[] args) {
        String str = "localhost";
        int port = 8887;
        MyWebsocketServer myWebsocketServer = new MyWebsocketServer(new InetSocketAddress(str, port));
        myWebsocketServer.run();
    }
}
