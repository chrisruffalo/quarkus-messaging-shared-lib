package org.example.shared.quarkus;

import org.eclipse.microprofile.reactive.messaging.Message;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class QuarkusMetadata implements Serializable {

    private Set<String> interceptionPoints = new HashSet<>();

    public QuarkusMetadata() {

    }

    public QuarkusMetadata(final String point) {
        this.interceptionPoints.add(point);
    }

    public String getInterceptionPointsString() {
        return interceptionPoints.stream().collect(Collectors.joining(", "));
    }

    public Set<String> getInterceptionPoints() {
        return Collections.unmodifiableSet(interceptionPoints);
    }

    public void setInterceptionPoints(Set<String> interceptionPoints) {
        this.interceptionPoints = interceptionPoints;
    }

    public void addInterceptionPoint(final String point) {
        this.interceptionPoints.add(point);
    }

    public static Message<?> addPointMetadata(Message<?> message, final String point) {
        if (message == null) {
            return null;
        }

        if(message.getMetadata(QuarkusMetadata.class).isPresent()) {
            QuarkusMetadata metadata = message.getMetadata(QuarkusMetadata.class).get();
            metadata.addInterceptionPoint(point);
            message = message.addMetadata(metadata);
        } else {
            message = message.addMetadata(new QuarkusMetadata(point));
        }

        return message;
    }
}
