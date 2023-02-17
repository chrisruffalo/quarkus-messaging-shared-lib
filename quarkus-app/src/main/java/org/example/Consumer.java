package org.example;

import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.example.shared.quarkus.QuarkusMetadata;
import org.example.shared.vanilla.VanillaMetadata;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

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

        final HashSet<String> interceptionPoints = new HashSet<>();

        final Optional<QuarkusMetadata> quarkusMetadataOptional = request.getMetadata(QuarkusMetadata.class);
        quarkusMetadataOptional.ifPresent(m -> interceptionPoints.addAll(m.getInterceptionPoints()));
        final Optional<VanillaMetadata> vanillaMetadataOptional = request.getMetadata(VanillaMetadata.class);
        vanillaMetadataOptional.ifPresent(m -> interceptionPoints.addAll(m.getInterceptionPoints()));

        if (interceptionPoints.isEmpty()) {
            logger.infof("payload: %s", payload);
        } else {
            logger.infof("payload: %s (interception points: %s)", payload, interceptionPoints.stream().collect(Collectors.joining(", ")));
        }

        return request.ack();
    }
}
