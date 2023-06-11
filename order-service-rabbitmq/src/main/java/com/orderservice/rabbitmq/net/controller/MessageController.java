package com.orderservice.rabbitmq.net.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.rabbitmq.net.dto.Order;
import com.orderservice.rabbitmq.net.dto.OrderEvent;
import com.orderservice.rabbitmq.net.producer.RabbitMQProducer;


@RestController
@RequestMapping("api/rabbitmq")
public class MessageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
	
	private RabbitMQProducer rabbitMQProducer;
	

	public MessageController(RabbitMQProducer rabbitMQProducer) {
		this.rabbitMQProducer = rabbitMQProducer;
	}
	

	@PostMapping("publish")
	public ResponseEntity<String> sendMessage(@RequestBody Order message){
		
		message.setOrderId(UUID.randomUUID().toString());
		OrderEvent orderEvent = new OrderEvent();
		orderEvent.setStatus("PENDING");
		orderEvent.setMessage("Order is in pening status");
		orderEvent.setOrder(message);
		
		this.rabbitMQProducer.sednMessage(orderEvent);
		return ResponseEntity.ok("Json Message Send successfully");
	}

}
