package org.goafabric.messageserver.adapter;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.publisher.GoaMessage;
import org.goafabric.messageserver.publisher.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AdvertisementAdapter {
    @Autowired
    private MessagePublisher messagePublisher;

    @JmsListener(destination = "patient.open")
    public void patientOpen(GoaMessage message) {
        logMessage(message);
    }

    @JmsListener(destination = "patient.close")
    public void patientClose(GoaMessage message) {
        logMessage(message);
    }

    @JmsListener(destination = "prescription.open")
    public void prescriptionOpen(GoaMessage message) {
        logMessage(message);
        messagePublisher.publish(new GoaMessage("banner.show",
                message.getReferenceId()));
    }

    @JmsListener(destination = "prescription.close")
    public void prescriptionClose(GoaMessage message) {
        logMessage(message);
    }

    private void logMessage(GoaMessage message) {
        log.info("Received message with topic {} and id {}"
                , message.getTopic(), message.getReferenceId());
    }

}
