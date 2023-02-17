package org.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class ResourceTest {

    @Inject
    Resource resource;

    @Test
    public void simple() {
        resource.send("some payload");
    }

}
