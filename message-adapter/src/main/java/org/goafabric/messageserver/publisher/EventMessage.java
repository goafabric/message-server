package org.goafabric.messageserver.publisher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventMessage implements Serializable {
    private String topic;
    private String referenceId;
    private Object object;
}
