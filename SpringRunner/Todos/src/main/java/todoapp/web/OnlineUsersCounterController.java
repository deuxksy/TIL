package todoapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import todoapp.web.support.ConnectedClientCountBroadcaster;

@Controller
public class OnlineUsersCounterController {

    private ConnectedClientCountBroadcaster broadcaster = new ConnectedClientCountBroadcaster();

    @RequestMapping(path = "/stream/online-users-counter", produces = "text/event-stream")
    public SseEmitter counter() {
        return broadcaster.subscribe();
    }

}