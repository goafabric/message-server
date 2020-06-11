package org.goafabric.messageserver.publisher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoaMessage {
    private String topic;
    private String referenceId;
    //private Object object;
}
