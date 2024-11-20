package com.fiap.guardian_bff.config;

import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitMqConfig {

    @Bean
    public SimpleRetryPolicy retryPolicy() {
        return new SimpleRetryPolicy(2);
    }

    @Bean
    public RetryTemplate retryTemplate(SimpleRetryPolicy retryPolicy) {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(retryPolicy);
        return retryTemplate;
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(
            ConnectionFactory connectionFactory, RetryTemplate retryTemplate) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDefaultRequeueRejected(false);
        container.setAdviceChain(RetryInterceptorBuilder.stateless()
                .retryOperations(retryTemplate)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build());
        return container;
    }
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
