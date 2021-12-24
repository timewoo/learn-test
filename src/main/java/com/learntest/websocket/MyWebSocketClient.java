package com.learntest.websocket;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author yanglin
 * @date 2021/3/1 21:27
 */
@Slf4j
public class MyWebSocketClient extends WebSocketClient {


    public MyWebSocketClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("Hello, it is me.Yang Lin :)");
        log.info("new connection opened");
    }

    @Override
    public void onMessage(String message) {
        log.info("received message:" + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("closed with exit code" + code + " additional info: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        log.info("an error occurred " + ex);
    }

    public static void main(String[] args) {
        try {
            MyWebSocketClient myWebSocketClient = new MyWebSocketClient(new URI("wss://stream.ayla.com.cn/stream?stream_key=4c8f5877f746457098b5aa1283291bc2&seq_start=10"));
            myWebSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
