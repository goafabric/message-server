package org.goafabric.messageserver.publisher;

import org.springframework.stereotype.Component;

@Component
public interface MessagePublisher {
    void publish(GoaMessage message);
}
