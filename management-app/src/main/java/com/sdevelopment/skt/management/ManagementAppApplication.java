package com.sdevelopment.skt.management;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ManagementAppApplication {

    @Value("${spring.rabbitmq.listqueue}")
    private String receivingQueue;

    @Value("${spring.rabbitmq.savequeue}")
    private String sendingQueue;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Bean
    Queue queue() {
        return new Queue(receivingQueue, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(receivingQueue);
    }

    public static void main(String[] args) {
        SpringApplication.run(ManagementAppApplication.class, args);
    }

}
