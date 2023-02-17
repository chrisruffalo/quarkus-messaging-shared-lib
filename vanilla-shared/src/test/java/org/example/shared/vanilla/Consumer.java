package org.example.shared.vanilla;

import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class Consumer {

    @Inject
    Logger logger;

    @Incoming("requests")
    @Outgoing("response")
    @Blocking
    public String process(String request) {
        return request;
    }

    @Incoming("response")
    @Blocking
    public CompletionStage<Void> process(Message<String> request) throws InterruptedException {

        final String payload = request.getPayload();
        final Optional<VanillaMetadata> metadataOptional = request.getMetadata(VanillaMetadata.class);

        if (metadataOptional.isPresent()) {
            logger.infof("request: %s (intercepted at: %s)", payload, metadataOptional.get().getInterceptionPointsString());
        } else {
            logger.infof("request: %s", payload);
        }

        return request.ack();
    }

}
