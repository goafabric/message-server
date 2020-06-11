package org.goafabric.messageserver.logic;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.dto.Email;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        log.info("Received <" + email + ">");
    }

}
