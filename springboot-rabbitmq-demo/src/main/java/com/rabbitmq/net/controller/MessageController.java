package com.rabbitmq.net.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.net.dto.User;
import com.rabbitmq.net.publish.RabbitMQJsonProducer;
import com.rabbitmq.net.publish.RabbitMQProducer;

@RestController
@RequestMapping("api/rabbitmq")
public class MessageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
	
	private RabbitMQProducer rabbitMQProducer;
	
	private RabbitMQJsonProducer rabbitMQJsonProducer;

	public MessageController(RabbitMQProducer rabbitMQProducer, RabbitMQJsonProducer rabbitMQJsonProducer) {
		this.rabbitMQProducer = rabbitMQProducer;
		this.rabbitMQJsonProducer = rabbitMQJsonProducer;
	}
	
	@GetMapping("publish")
	public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
		
		this.rabbitMQProducer.sendMesage(message);
		return ResponseEntity.ok("Message Send successfully");
	}
	
	@PostMapping("jsonPublish")
	public ResponseEntity<String> sendMessage(@RequestBody User message){
		
		this.rabbitMQJsonProducer.sendMesage(message);
		return ResponseEntity.ok("Json Message Send successfully");
	}

}
