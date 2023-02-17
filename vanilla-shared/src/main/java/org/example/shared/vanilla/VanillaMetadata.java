package org.example.shared.vanilla;

import org.eclipse.microprofile.reactive.messaging.Message;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class VanillaMetadata implements Serializable {

    private Set<String> interceptionPoints = new HashSet<>();

    public VanillaMetadata() {

    }

    public VanillaMetadata(final String point) {
        this.interceptionPoints.add(point);
    }

    public String getInterceptionPointsString() {
        return interceptionPoints.stream().collect(Collectors.joining(", "));
    }

    public Set<String> getInterceptionPoints() {
        return Collections.unmodifiableSet(interceptionPoints);
    }

    public void addInterceptionPoint(final String point) {
        this.interceptionPoints.add(point);
    }

    public static Message<?> addPointMetadata(Message<?> message, final String point) {
        if (message == null) {
            return null;
        }

        if(message.getMetadata(VanillaMetadata.class).isPresent()) {
            VanillaMetadata metadata = message.getMetadata(VanillaMetadata.class).get();
            metadata.addInterceptionPoint(point);
            message = message.addMetadata(metadata);
        } else {
            message = message.addMetadata(new VanillaMetadata(point));
        }

        return message;
    }
}
