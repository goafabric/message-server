package org.goafabric.messaging.configuration;

import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

public final class ExcludeAutoConfiguration {
    private ExcludeAutoConfiguration() {
    }

    public static final String excludeClass = "org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration";
}
