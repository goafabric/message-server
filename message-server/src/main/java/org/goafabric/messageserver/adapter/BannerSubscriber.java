package org.goafabric.messageserver.adapter;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.publisher.GoaMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BannerSubscriber {
    @JmsListener(destination = "banner.show")
    public void bannerShow(GoaMessage message) {
        log.info("Received message with topic {} and id {}"
                , message.getTopic(), message.getReferenceId());
    }
}
