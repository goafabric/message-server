package org.goafabric.messageserver;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.dto.MyMessage;
import org.goafabric.messageserver.dto.Person;
import org.goafabric.messageserver.logic.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Message;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class SendAndReceiveIT {
    @Autowired
    private Sender sender;

    @Test
    public void test() throws InterruptedException {
        sender.sendMessage(new MyMessage("1", "PERSON_OPEN", new Person()));
        Thread.sleep(5000);
    }
}
