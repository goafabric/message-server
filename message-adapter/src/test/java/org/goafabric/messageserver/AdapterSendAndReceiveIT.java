package org.goafabric.messageserver;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.publisher.EventMessage;
import org.goafabric.messageserver.publisher.MessagePublisher;
import org.goafabric.messageserver.dto.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class AdapterSendAndReceiveIT {
    @Autowired
    private MessagePublisher messagePublisher;

    @Test
    public void test() throws InterruptedException {
        final Patient patient = Patient.builder()
                .firstName("Homer").lastName("Simpson").build();

        messagePublisher.publish(EventMessage.builder()
                .topic("patient.open").referenceId("100").object(patient).build());
        messagePublisher.publish(EventMessage.builder()
                .topic("prescription.open").referenceId("1000").build());
        messagePublisher.publish(EventMessage.builder()
                .topic("prescription.close").referenceId("1000").build());
        messagePublisher.publish(EventMessage.builder()
                .topic("patient.close").referenceId("100").build());
        Thread.sleep(2000);
    }
}
