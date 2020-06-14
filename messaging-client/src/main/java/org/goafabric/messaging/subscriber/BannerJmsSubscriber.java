/*
package org.goafabric.messaging.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messaging.publisher.EventMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BannerJmsSubscriber {
    @JmsListener(destination = "banner.show")
    public void bannerShow(EventMessage message) {
        log.info("Received message with topic {} and id {}"
                , message.getTopic(), message.getReferenceId());
    }
}
 */
