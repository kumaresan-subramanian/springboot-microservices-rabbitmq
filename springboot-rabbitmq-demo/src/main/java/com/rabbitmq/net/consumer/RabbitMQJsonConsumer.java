package com.rabbitmq.net.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmq.net.dto.User;
import com.rabbitmq.net.publish.RabbitMQProducer;

@Service
public class RabbitMQJsonConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
	
	
	@RabbitListener(queues = "${rabbitmq.queue.json.name}")
	public void consumeMessage(User message) {
		LOGGER.info(String.format("json Message received : %s", message.toString()));
	}

}
