package org.goafabric.messaging.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.MessageQueue;
import org.goafabric.messaging.publisher.EventMessage;
import org.goafabric.messaging.service.TenantIdStorage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BannerSubscriber {
    @JmsListener(destination = MessageQueue.BANNER_SHOW)
    @RabbitListener(queues = MessageQueue.BANNER_SHOW)
    public void bannerShow(EventMessage message) {
        //very simple tenant check, we could also create seperate specific queues including TENANT_ID, which could than overload the MessageBroker
        if (TenantIdStorage.getTenantId().equals(message.getTenantId())) {
            log.info("Received message with topic {} and id {} for tenant {}"
                    , message.getQueue(), message.getReferenceId(), message.getTenantId());
        }
    }
}

