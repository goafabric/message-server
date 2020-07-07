package org.goafabric.messaging;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messaging.service.ClientService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class ClientSendAndReceiveIT {
    @Autowired
    private ClientService clientService;

    @Test
    public void test()  {
        clientService.patientOpen();
        clientService.prescriptionOpen();
        clientService.prescriptionClose();
        clientService.patientClose();
    }
}
