package com.niit.controlles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.niit.dto.Chat;

@Controller
public class SockController {

	//to send data to the client
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	//list of username's who has joined the chat room
	private List<String> users=new ArrayList<String>();
	//$stompClient.subscribeMapping("/app/join/ayush")
	@SubscribeMapping("/join/{username}")//send and receiving message using subscribe mapping or reterning message to client
	public List<String> sandy(@DestinationVariable String username){
	if(!users.contains(username))
		 users.add(username);
	messagingTemplate.convertAndSend("/topic/join",username);//all the other users who has already joined the chat room
	return users;//newly joind users
}
	@MessageMapping("/chat") //receiving message using subscribe mapping
	public void chatMessage(Chat chat){ //to from message
		//group chat
		if(chat.getTo().equals("all")){
			messagingTemplate.convertAndSend("/queue/chats" ,chat);
		}
		//from:john ,to:smith
		else{
			messagingTemplate.convertAndSend("/queue/chats/"+chat.getTo(),chat);
			messagingTemplate.convertAndSend("queue/chats/"+chat.getFrom(),chat);
		}
		
	}
}