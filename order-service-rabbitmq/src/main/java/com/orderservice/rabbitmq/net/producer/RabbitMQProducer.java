package com.orderservice.rabbitmq.net.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orderservice.rabbitmq.net.dto.OrderEvent;


@Service
public class RabbitMQProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.key.name}")
	private String routingKey;
	
	RabbitTemplate rabbitTemplate;

	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sednMessage(OrderEvent event) {
		LOGGER.info(String.format("Message sent : %s", event.toString()));
		this.rabbitTemplate.convertAndSend(exchange, routingKey, event);
	}
}
