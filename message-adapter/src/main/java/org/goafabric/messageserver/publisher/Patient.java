package org.goafabric.messageserver.publisher;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class Patient implements Serializable {
    private String firstName;
    private String lastName;
}
