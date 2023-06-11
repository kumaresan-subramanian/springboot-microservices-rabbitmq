package com.stockservice.rabbitmq.net.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.stockservice.rabbitmq.net.dto.OrderEvent;
@Service
public class RabbitMQConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
	
	
	@RabbitListener(queues = "${rabbitmq.queue.name}")
	public void consumeMessage(OrderEvent message) {
		LOGGER.info(String.format("Message received : %s", message.toString()));
	}

}
