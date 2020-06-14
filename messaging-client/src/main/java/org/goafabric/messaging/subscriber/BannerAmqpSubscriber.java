package org.goafabric.messaging.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messaging.publisher.EventMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BannerAmqpSubscriber {
    @RabbitListener(queues = "banner.show")
    public void bannerShow(EventMessage message) {
        log.info("Received message with topic {} and id {}"
                , message.getTopic(), message.getReferenceId());
    }
}
