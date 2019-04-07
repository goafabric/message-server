package org.goafabric.messageserver.logic;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage() {
        log.info("sending email");
        jmsTemplate.convertAndSend("mailbox",
                new Email("info@example.com", "Hello"));

    }

}
