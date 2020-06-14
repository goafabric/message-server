package org.goafabric.messageserver.configuration;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.messageserver.publisher.EventMessage;
import org.goafabric.messageserver.publisher.MessagePublisher;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.HashMap;
import java.util.Map;

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

    /*
    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        converter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("event", EventMessage.class);
        converter.setTypeIdMappings(typeIdMappings);

        return converter;
    }
     */

    @Bean
    public MessagePublisher jmsMessagePublisher(JmsTemplate jmsTemplate) {
        return message -> {
            log.info("Sending message with topic {} and id {}"
                    , message.getTopic(), message.getReferenceId());
            jmsTemplate.convertAndSend(message.getTopic(), message);
            jmsTemplate.send(message.getTopic(), new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(message);
                }
            });
        };

    }
}
