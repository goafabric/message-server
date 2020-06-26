package org.goafabric.messaging.configuration;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messaging.publisher.MessagePublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MessageBrokerConfiguration {
    public static final String excludeClass = "org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration";

    @Bean
    public MessagePublisher messagePublisher(RabbitTemplate rabbitTemplate) {
        return message -> {
            log.info("Sending message with topic {} and id {}"
                    , message.getQueue(), message.getReferenceId());
            rabbitTemplate.convertAndSend(message.getQueue(), message);
        };
    }

    /*
    @Bean
public AmqpAdmin amqpAdmin() {
    return new RabbitAdmin(connectionFactory);
}
Then you add it to your service:

@Autowired
private AmqpAdmin admin;
Finally you can use it to create queues and bindings.

Queue queue = new Queue(queueName, durable, false, false);
Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, EXCHANGE, routingKey, null);
admin.declareQueue(queue);
admin.declareBinding(binding);
     */
}
