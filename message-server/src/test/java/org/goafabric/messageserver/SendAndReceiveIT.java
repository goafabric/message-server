package org.goafabric.messageserver;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.dto.MyMessage;
import org.goafabric.messageserver.dto.Person;
import org.goafabric.messageserver.logic.MessagePublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class SendAndReceiveIT {
    @Autowired
    private MessagePublisher messagePublisher;

    @Test
    public void test() throws InterruptedException {
        messagePublisher.publish(new MyMessage("PERSON_OPEN", "1", new Person()));
        Thread.sleep(5000);
    }
}
