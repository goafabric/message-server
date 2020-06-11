package org.goafabric.messageserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyMessage {
    private String topic;
    private String referenceId;
    private Object object;
}
