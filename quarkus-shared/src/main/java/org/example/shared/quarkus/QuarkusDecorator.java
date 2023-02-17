package org.example.shared.quarkus;

import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.PublisherDecorator;
import io.smallrye.reactive.messaging.SubscriberDecorator;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class QuarkusDecorator implements PublisherDecorator, SubscriberDecorator {

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public Multi<? extends Message<?>> decorate(Multi<? extends Message<?>> multi, String s, boolean b) {
        return multi.map(message -> QuarkusMetadata.addPointMetadata(message, "quarkus publish"));
    }

    @Override
    public Multi<? extends Message<?>> decorate(Multi<? extends Message<?>> multi, List<String> list, boolean b) {
        return multi.map(message -> QuarkusMetadata.addPointMetadata(message, "quarkus subscribe"));
    }
}
