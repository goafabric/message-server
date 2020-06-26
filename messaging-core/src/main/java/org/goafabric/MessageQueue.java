package org.goafabric;


import java.util.ArrayList;
import java.util.List;

public class MessageQueue {
    private MessageQueue() {
    }

    public static final String PATIENT_OPEN = "patient.open";
    public static final String PATIENT_CLOSE = "patient.close";
    public static final String PRESCRIPTION_OPEN = "prescription.open";
    public static final String PRESCRIPTION_CLOSE = "prescription.close";
    public static final String BANNER_SHOW = "banner.show";

    public static List<String> getAllQueues() {
        final List<String> queues = new ArrayList<>();
        queues.add(PATIENT_OPEN);
        queues.add(PATIENT_CLOSE);
        queues.add(PRESCRIPTION_OPEN);
        queues.add(PRESCRIPTION_CLOSE);
        return queues;
    }
}
