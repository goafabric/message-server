package org.goafabric.messaging.adapter;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.MessageQueue;
import org.goafabric.messaging.dto.Patient;
import org.goafabric.messaging.publisher.EventMessage;
import org.goafabric.messaging.publisher.MessagePublisher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AdvertisementAdapter {
    @Autowired
    private MessagePublisher messagePublisher;

    @JmsListener(destination = MessageQueue.PATIENT_OPEN)
    @RabbitListener(queues = MessageQueue.PATIENT_OPEN)
    public void patientOpen(EventMessage message) {
        logMessage(message);
    }

    @JmsListener(destination = MessageQueue.PATIENT_CLOSE)
    @RabbitListener(queues = MessageQueue.PATIENT_CLOSE)
    public void patientClose(EventMessage message) {
        logMessage(message);
    }

    @JmsListener(destination = MessageQueue.PRESCRIPTION_OPEN)
    @RabbitListener(queues = MessageQueue.PRESCRIPTION_OPEN)
    public void prescriptionOpen(EventMessage message) {
        logMessage(message);
        //send back banner.show
        messagePublisher.publish(EventMessage.builder()
                .queue("banner.show").referenceId(message.getReferenceId()).tenantId(message.getTenantId()).build());
    }

    @JmsListener(destination = MessageQueue.PRESCRIPTION_CLOSE)
    @RabbitListener(queues = MessageQueue.PRESCRIPTION_CLOSE)
    public void prescriptionClose(EventMessage message) {
        logMessage(message);
    }

    private void logMessage(EventMessage message) {
        log.info("Received message with topic {} and id {}"
                , message.getQueue(), message.getReferenceId());

        if (message.getObject() instanceof Patient) {
            final Patient patient = (Patient) message.getObject();
            log.info("Got Patient object {}", patient.toString());
        }
    }

}
