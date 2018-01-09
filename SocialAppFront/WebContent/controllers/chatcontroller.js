/**
 * ChatController
 */
app.controller('ChatCtrl',function($rootScope,$scope,socket){
	$socket.chats=[];
	$scope.stompClient=socket.stompClient;
	$scope.users=[];
	
	
	$scope.$on('sockConnected',function(event,frame){// connected with webscoket
		$Scope.userName=$rootScope.currentUser.username;
		
		//server to client[receving username from server]
		//frame is used for STOMP protocol
		//Event is (ur enter into chat room)
		//we are passing the frame to websocket for connection 
		//client subscribe the message call the function for call back
		//data is username, type is string
		
		$scope.stompClient.subscribe("/topic/join",function(message){
			//convert string to JSON
			user=JSON.parse(message.body)
			console.log(user)
			if(user != $scope.userName && $.inArray(user,$scope.users)== -1){
				$scope.addUser(user);
				$scope.latestUser = user;
				$scope.$apply();
				$('#joinedChat').fadeIn(100).delay(10000).fadeOut(200);
			}
		})
		//send data from client to server
		//data is username
		//data from server to client is list of username's
		$scope.stompClient.subscribe("app/join/"+$scope.userName,function(message){
			$scope.users=JSON.parse(message.body)
			$scope.$apply();
		})
		
		$scope.addUser = function(user){
			$scope.users.push(user);
			$scope.$apply();
		}
	})
	
	
		$scope.$on('sockConnected',function(event ,frame){
		  $scope.userName = $rootScope.currentUser.username;	
			
		$scope.user=$rootScope.currentUser.username;
		
		//private chat
		$scope.stompClient.subscribe("/queue/chats/"+$scope.userName,function(message){
			console.log("message for" +$scope.userName)
			$scope.processIncomingMessage(message, false); //broadcast=false for private cht
		});
		
		//group chat
		$scope.stompClient.subscribe("/queue/chats/",function(message){
			$scope.processIncomingMessage(message , true); //broadcast =true
		});
	});
	
	$scope.capitalize=function(str){
		return str.charAt(0).toUpperCase() + str.slice(1);
	};
	
	$scope.sendMessage = function(chat){
		chat.from = $scope.userName;
		$scope.stompClient.send("/app/chat",{},JSON.stringify(chat));
		$rootScope.$broadcast('sendingChat',chat);
		$scope.chat.message='';
	};
	
	//to add chat message to the chat array $scope.chats
	$scope.processIncomingMessage = function(message,broadcast){
		message = JSON.parse(message.body);
		message.direction = 'incoming';
		//only for sandy if condition is true
		if (message.from != $scope.userName){
			$scope.addChat(message);
			$scope.$apply(); //since insidesubscribe closure
		}
	};
	
	$scope.addChat= function(chat){
		$scope.chats.push(chat);
	};
	//for james
	$scope.$on('sendingChat',function(event,sentChat){
		chat = angular.copy(sentChat);
		chat.from = 'Me';
		chat.direction = 'outgoing';
		$scope.addChat(chat);
	});
		

	
})