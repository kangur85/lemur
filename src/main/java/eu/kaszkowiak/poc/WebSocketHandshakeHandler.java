package eu.kaszkowiak.poc;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * Created by kan on 15.01.17.
 */
//@Configuration -- unused at the moment
public class WebSocketHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        Principal principal = super.determineUser(request, wsHandler, attributes);
        if (principal == null) {
            principal = () -> "abc"; //FIXME - set to session id
        }
        return principal;
    }
}
