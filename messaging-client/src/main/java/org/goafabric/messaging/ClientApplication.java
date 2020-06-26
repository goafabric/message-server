
package org.goafabric.messaging;

import org.goafabric.messaging.configuration.MessageBrokerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(excludeName = MessageBrokerConfiguration.excludeClass)
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
