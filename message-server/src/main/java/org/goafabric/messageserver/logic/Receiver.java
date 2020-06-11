package org.goafabric.messageserver.logic;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.dto.MyMessage;
import org.goafabric.messageserver.dto.Person;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {

    @JmsListener(destination = "PERSON_OPEN", containerFactory = "myFactory")
    public void receiveMessage(MyMessage message) {
        //final Person person = (Person) message.getObject();
        log.info("Received message with topic {} and Person {}", message.getTopic());
    }

}
