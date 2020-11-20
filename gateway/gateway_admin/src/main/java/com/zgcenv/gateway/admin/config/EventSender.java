package com.zgcenv.gateway.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

//@Component

public class EventSender {
    private static final Logger log = LoggerFactory.getLogger(EventSender.class);

//    @Resource
    private RabbitTemplate rabbitTemplate;

//    @Resource
    private MessageConverter messageConverter;

//    @PostConstruct
    public void init() {
        rabbitTemplate.setMessageConverter(messageConverter);
    }

    public void send(String routingKey, Object object) {
        log.info("routingKey:{}=>message:{}", routingKey, object);
        rabbitTemplate.convertAndSend(BusConfig.EXCHANGE_NAME, routingKey, object);
    }
}
