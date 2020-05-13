import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/action")
public class WebSocketServer {
    private static Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
    private static final Logger logger = LogManager.getLogger(WebSocketServer.class);

    @OnMessage
    public void onMessage(Session session, String msg) {
        try {
            logger.info("received msg " + msg + " from " + session.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        JSONObject json = new JSONObject(msg);
        String command = json.getString("command");

        if (command.equals("search")) {
            // get starting x and y
            int startP = json.getInt("start");
            int endP = json.getInt("end");
            WebSocketSession webSocketSession = findSession(session);
            if (webSocketSession != null) {
                webSocketSession.makeSearch(startP, endP);
            }
        }
    }

    @OnOpen
    public void open(Session session) {
        sessions.add(new WebSocketSession(session));
        logger.info("New session opened: " + session.getId());
    }

    @OnError
    public void error(Session session, Throwable t) {
        WebSocketSession webSocketSession = findSession(session);
        if (webSocketSession != null) {
            sessions.remove(webSocketSession);
        }
        logger.error("Error on session " + session.getId() + t.getMessage());
    }

    @OnClose
    public void closedConnection(Session session) {
        WebSocketSession webSocketSession = findSession(session);
        if (webSocketSession != null) {
            sessions.remove(webSocketSession);
        }
        logger.info("session closed: " + session.getId());
    }

    private WebSocketSession findSession(Session session) {
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.session == session) {
                return webSocketSession;
            }
        }
        return null;
    }
}
