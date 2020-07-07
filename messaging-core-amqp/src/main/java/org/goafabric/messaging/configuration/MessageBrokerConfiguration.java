package org.goafabric.messaging.configuration;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.MessageQueue;
import org.goafabric.messaging.publisher.MessagePublisher;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MessageBrokerConfiguration {
    public static final String EXCLUDE_CLASS = "org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration";

    @Bean
    public MessagePublisher messagePublisher(RabbitTemplate rabbitTemplate) {
        return message -> {
            log.info("Sending message with topic {} and id {}"
                    , message.getQueue(), message.getReferenceId());
            rabbitTemplate.convertAndSend(message.getQueue(), message);
        };
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        final AmqpAdmin amqpAdmin =  new RabbitAdmin(connectionFactory);

        for (String queueName : MessageQueue.getAllQueues()) {
            Queue queue = new Queue(queueName, Boolean.TRUE, false, false);
            amqpAdmin.declareQueue(queue);
        }
        return amqpAdmin;
    }

}