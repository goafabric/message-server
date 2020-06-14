package org.goafabric.messageserver.adapter;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.publisher.EventMessage;
import org.goafabric.messageserver.publisher.MessagePublisher;
import org.goafabric.messageserver.publisher.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AdvertisementAdapter {
    @Autowired
    private MessagePublisher messagePublisher;

    @JmsListener(destination = "patient.open")
    public void patientOpen(EventMessage message) {
        logMessage(message);
    }

    @JmsListener(destination = "patient.close")
    public void patientClose(EventMessage message) {
        logMessage(message);
    }

    @JmsListener(destination = "prescription.open")
    public void prescriptionOpen(EventMessage message) {
        logMessage(message);
        messagePublisher.publish(new EventMessage("banner.show",
                message.getReferenceId(), new String("YO")));
    }

    @JmsListener(destination = "prescription.close")
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
