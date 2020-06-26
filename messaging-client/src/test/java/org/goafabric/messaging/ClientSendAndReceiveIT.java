package org.goafabric.messaging;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.MessageQueue;
import org.goafabric.messaging.publisher.EventMessage;
import org.goafabric.messaging.publisher.MessagePublisher;
import org.goafabric.messaging.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.goafabric.messaging.dto.Patient;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class ClientSendAndReceiveIT {
    @Autowired
    private ClientService clientService;

    @Test
    public void test() throws InterruptedException {
        clientService.patientOpen();
        clientService.prescriptionOpen();
        clientService.prescriptionClose();
        clientService.patientClose();
        Thread.sleep(2000);
    }
}
