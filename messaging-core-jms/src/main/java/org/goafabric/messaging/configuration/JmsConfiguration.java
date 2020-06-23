package org.goafabric.messaging.configuration;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messaging.publisher.MessagePublisher;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
@Slf4j
public class JmsConfiguration {
    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setErrorHandler(errorHandler());
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public ErrorHandler errorHandler() {
        return throwable -> {
            log.warn("In default jms error handler...");
            log.error("Error Message : {}", throwable.getMessage());
        };
    }

    @Bean
    public MessagePublisher jmsMessagePublisher(JmsTemplate jmsTemplate) {
        return message -> {
            log.info("Sending message with topic {} and id {}"
                    , message.getTopic(), message.getReferenceId());
            jmsTemplate.convertAndSend(message.getTopic(), message);
            //jmsTemplate.send(message.getTopic(),
              //      session -> session.createObjectMessage(message));
        };

    }
}
