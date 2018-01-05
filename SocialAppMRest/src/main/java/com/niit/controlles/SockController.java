package com.niit.controlles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SockController {

	//to send data to the client
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	//list of username's who has joined the chat room
	private List<String> users=new ArrayList<String>();
	//$stompClient.subscribeMapping("/app/sandy/ayush")
	@SubscribeMapping("/sandy/{username}")
	public List<String> sandy(@DestinationVariable String username){
	if(!users.contains(username))
		 users.add(username);
	messagingTemplate.convertAndSend("/topic/sandy",username);//all the other users who has already joined the chat room
	return users;
}
}