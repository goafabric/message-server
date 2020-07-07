package org.goafabric.messaging.service;


import org.goafabric.MessageQueue;
import org.goafabric.messaging.dto.Patient;
import org.goafabric.messaging.publisher.EventMessage;
import org.goafabric.messaging.publisher.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/queues",
        produces = MediaType.APPLICATION_JSON_VALUE)

public class ClientService {

    @Autowired
    private MessagePublisher messagePublisher;

    @GetMapping("patientOpen")
    public String patientOpen() {
        final Patient patient = Patient.builder()
                .firstName("Homer").lastName("Simpson").build();

        messagePublisher.publish(EventMessage.builder()
                .queue(MessageQueue.PATIENT_OPEN).referenceId("100")
                .tenantId(TenantIdStorage.getTenantId()).object(patient).build());
        return "Patient opened with id 100";
    }

    @GetMapping("patientClose")
    public String patientClose() {
        messagePublisher.publish(EventMessage.builder()
                .queue(MessageQueue.PATIENT_CLOSE).referenceId("100")
                .tenantId(TenantIdStorage.getTenantId()).build());
        return "Patient closed with id 100";
    }

    @GetMapping("prescriptionOpen")
    public String prescriptionOpen() {
        messagePublisher.publish(EventMessage.builder()
                .queue(MessageQueue.PRESCRIPTION_OPEN).referenceId("1000")
                .tenantId(TenantIdStorage.getTenantId()).build());
        return "Prescription opened with id 1000";
    }

    @GetMapping("prescriptionClose")
    public String prescriptionClose() {
        messagePublisher.publish(EventMessage.builder()
                .queue(MessageQueue.PRESCRIPTION_CLOSE).referenceId("1000")
                .tenantId(TenantIdStorage.getTenantId()).build());
        return "Prescription closed with id 1000";
    }
}
