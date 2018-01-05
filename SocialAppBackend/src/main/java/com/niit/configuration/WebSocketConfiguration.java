package com.niit.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//enable broker based stomp messaging 
                             //authantication annotation @MessageMapping , @SubscribeMapping
@ComponentScan(basePackages="com.niit")
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer{

	public void configureClientInboundChannel(ChannelRegistration arg0) {
	
	}

	public void configureClientOutboundChannel(ChannelRegistration arg0) {
		// TODO Auto-generated method stub
		
	}

	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//enablesimplbroker destination prefixes - by spring controller to send message to client
		//to send data from server to client
		//topic - to notify the newly joined username
		//queue - to send chat message
		//communication direction - from server to client
		//server user direction /queue , /topic to send message to client
		//client will received the message by subscribing  $scope.subscribe("/topic/sandy",...)
		registry.enableSimpleBroker("/queue/" , "/topic/");//server to client
		
		//client to server -destination prefix as /app
		//in client side .. send("/app/sandy",...)
        registry.setApplicationDestinationPrefixes("/app"); //client to server		
	}

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// JS Stomp.over("../chatmodule")
		registry.addEndpoint("/chatmodule").withSockJS();
	}

}
