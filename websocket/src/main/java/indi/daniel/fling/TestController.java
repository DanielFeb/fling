package indi.daniel.fling;

import org.java_websocket.client.WebSocketClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class TestController {

    @GetMapping("hello")
    public String helloWorld() {
        return "hello world!";
    }

    @GetMapping("socket")
    public String testSocket() throws URISyntaxException {
        WebSocketClient client= new MyWebSocketClient(new URI("ws://127.0.0.1:8001/websocket/count"));
        client.connect();
        return "success";
    }
}
