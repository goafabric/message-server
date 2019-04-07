package org.goafabric.messageserver;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.logic.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration
@RunWith(SpringRunner.class)
@Import(TestConfiguration.class)
@Slf4j
public class SenderIT {
    @Autowired
    private Sender sender;

    @Test
    public void test() throws InterruptedException {
        sender.sendMessage();

        while (true) {

        }
    }
}
