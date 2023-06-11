package com.rabbitmq.net.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.queue.name}")
	private String queue;
	
	@Value("${rabbitmq.queue.json.name}")
	private String jsonQueue;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.key.name}")
	private String routingKey;
	
	@Value("${rabbitmq.routing.json.key.name}")
	private String routingJsonKey;
	
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueue);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue())
				.to(exchange())
				.with(routingKey);
	}
	
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder.bind(jsonQueue())
				.to(exchange())
				.with(routingJsonKey);
	}
	
	
	@Bean
	MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	AmqpTemplate amqptemplate(ConnectionFactory connectionfactory) {
		RabbitTemplate rabbittemplate = new RabbitTemplate(connectionfactory);
		rabbittemplate.setMessageConverter(converter());
		return rabbittemplate;
	}
	// below beans autoconfigured by spring boot
	//Connectionfactory
	//RabbitTemplate
	//RabbitAdmin
}
