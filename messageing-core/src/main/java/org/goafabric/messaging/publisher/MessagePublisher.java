package org.goafabric.messaging.publisher;

import org.springframework.stereotype.Component;

@Component
public interface MessagePublisher {
    void publish(EventMessage message);
}
