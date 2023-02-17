package org.example.shared.vanilla;

import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@ApplicationScoped
@Path("/")
public class Producer {

    @Channel("requests")
    Emitter<String> testRequestEmitter;

    @POST
    @Blocking
    public void send(final String payload) {
        final Message<String> message = Message.of(payload);
        testRequestEmitter.send(message);
    }

}
