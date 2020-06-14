package org.goafabric.messaging.configuration;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messaging.publisher.MessagePublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AmqpConfiguration {
    @Bean
    public MessagePublisher jmsMessagePublisher(RabbitTemplate rabbitTemplate) {
        return message -> {
            log.info("Sending message with topic {} and id {}"
                    , message.getTopic(), message.getReferenceId());
            rabbitTemplate.convertAndSend(message.getTopic(), message);
        };

    }
}
