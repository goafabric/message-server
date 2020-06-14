package org.goafabric.messaging.adapter;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messaging.dto.Patient;
import org.goafabric.messaging.publisher.EventMessage;
import org.goafabric.messaging.publisher.MessagePublisher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AdvertisementAmqpAdapter {
    @Autowired
    private MessagePublisher messagePublisher;

    @RabbitListener(queues = "patient.open")
    public void patientOpen(EventMessage message) {
        logMessage(message);
    }

    @RabbitListener(queues = "patient.close")
    public void patientClose(EventMessage message) {
        logMessage(message);
    }

    @RabbitListener(queues = "prescription.open")
    public void prescriptionOpen(EventMessage message) {
        logMessage(message);
        messagePublisher.publish(new EventMessage("banner.show",
                message.getReferenceId(), null));
    }

    @RabbitListener(queues = "prescription.close")
    public void prescriptionClose(EventMessage message) {
        logMessage(message);
    }

    private void logMessage(EventMessage message) {
        log.info("Received message with topic {} and id {}"
                , message.getTopic(), message.getReferenceId());

        if (message.getObject() instanceof Patient) {
            final Patient patient = (Patient) message.getObject();
            log.info("Got Patient object {}", patient.toString());
        }
    }

}
