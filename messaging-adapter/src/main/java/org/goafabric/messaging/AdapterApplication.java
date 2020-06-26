
package org.goafabric.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

@SpringBootApplication(exclude = RabbitAutoConfiguration.class)
public class AdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdapterApplication.class, args);
    }

}
