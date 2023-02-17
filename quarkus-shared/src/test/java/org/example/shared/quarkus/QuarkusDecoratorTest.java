package org.example.shared.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class QuarkusDecoratorTest {

    @Inject
    Producer producer;

    @Test
    public void simple() {
        producer.send("one");
        producer.send("two");
    }

}
