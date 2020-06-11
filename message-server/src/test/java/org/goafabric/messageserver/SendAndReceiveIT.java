package org.goafabric.messageserver;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.publisher.GoaMessage;
import org.goafabric.messageserver.publisher.MessagePublisher;
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
        messagePublisher.publish(new GoaMessage("patient.open", "100"));
        messagePublisher.publish(new GoaMessage("prescription.open", "1000"));
        messagePublisher.publish(new GoaMessage("prescription.close", "1000"));
        messagePublisher.publish(new GoaMessage("patient.close", "100"));
        Thread.sleep(2000);
    }
}
