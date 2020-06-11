package org.goafabric.messageserver.logic;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.dto.Email;
import org.goafabric.messageserver.dto.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(MyMessage message) {
        log.info("sending message with topic {}", message.getTopic());
        jmsTemplate.convertAndSend(message.getTopic(), message);
    }

}
