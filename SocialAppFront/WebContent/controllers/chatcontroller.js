/**
 * ChatController
 */

app.controller('ChatCtrl', ['$rootScope' ,'$scope', 'socket', function($rootScope ,$scope, socket) {
    alert('entering chat controller')
    $scope.chats = [];
    $scope.stompClient = socket.stompClient;
    $scope.users=[]
    $scope.$on('sockConnected', function(event, frame) {
    	alert('sockconnected')
        $scope.userName=$rootScope.currentUser.username;
    	
        $scope.stompClient.subscribe("/topic/join", function(message) {
        	
            user = JSON.parse(message.body);
            console.log(user)
           
            if(user != $scope.userName && $.inArray(user, $scope.users) == -1) {
                $scope.addUser(user);
                $scope.latestUser = user;
                $scope.$apply();
                $('#joinedChat').fadeIn(500).delay(2000).fadeOut(500);
            }
            
        });
        
  
        $scope.stompClient.subscribe('/app/join/'+$scope.userName, function(message) {
        
            $scope.users = JSON.parse(message.body);
        	
            $scope.$apply();
        });
        
    });

    $scope.sendMessage = function(chat) {
        chat.from = $scope.userName;
      
        $scope.stompClient.send("/app/chat", {}, JSON.stringify(chat));
        $rootScope.$broadcast('sendingChat', chat);
        $scope.chat.message = '';

    };

    $scope.capitalize = function(str) {
        return str.charAt(0).toUpperCase() + str.slice(1);
    };
 
    $scope.addUser = function(user) {
        $scope.users.push(user);
        $scope.$apply();
    };
 
    
    
    
    
    
    
    $scope.$on('sockConnected', function(event, frame) {
        $scope.userName=$rootScope.currentUser.username;
  
        $scope.user=$rootScope.currentUser.username;
       
        $scope.stompClient.subscribe( "/queue/chats/"+$scope.userName, function(message) {
        	
            $scope.processIncomingMessage(message, false);
        });
        
        
        $scope.stompClient.subscribe("/queue/chats", function(message) {
        	
            $scope.processIncomingMessage(message, true);
        });
        
        
    });

    $scope.$on('sendingChat', function(event, sentChat) {
        chat = angular.copy(sentChat);
        chat.from = 'Me';
        chat.direction = 'outgoing';
        $scope.addChat(chat);
    });

    $scope.processIncomingMessage = function(message, isBroadcast) {
        message = JSON.parse(message.body);
        message.direction = 'incoming';
	message.broadcast=isBroadcast
        if(message.from != $scope.userName) {
        	$scope.addChat(message);
            $scope.$apply(); // since inside subscribe closure
        }
    };

 
    $scope.addChat = function(chat) {
        $scope.chats.push(chat);
    };
 
 
}]);


/*app.controller('ChatCtrl',function($rootScope,$scope,socket){
	$socket.chats=[];
	$scope.stompClient=socket.stompClient;
	$scope.users=[];
	
	
	$scope.$on('sockConnected',function(event,frame){// connected with webscoket
		alert('Sockconnected')
		$Scope.userName=$rootScope.currentUser.username;
		
		//server to client[receving username from server]
		//frame is used for STOMP protocol
		//Event is (ur enter into chat room)
		//we are passing the frame to websocket for connection 
		//client subscribe the message call the function for call back
		//data is username, type is string
		
		$scope.stompClient.subscribe("/topic/join",function(message){ //funtion (message) is list pass newly join user in list
			//convert string to JSON
			user=JSON.parse(message.body)//message.body is the chat object in the form of string and message is the 
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
	
	$scope.capitalize=function(str){//capitalize is function is used to display the first character in upper case
		return str.charAt(0).toUpperCase() + str.slice(1);
	};
	
	$scope.sendMessage = function(chat){
		chat.from = $scope.userName;//userName is the current user
		$scope.stompClient.send("/app/chat",{},JSON.stringify(chat));// 
		$rootScope.$broadcast('sendingChat',chat);// sending chat is the id of the event that is call the $on('sendingchat')
		$scope.chat.message='';
	};
	
	//to add chat message to the chat array $scope.chats
	$scope.processIncomingMessage = function(message,broadcast){
		message = JSON.parse(message.body);//sting to Json
		message.direction = 'incoming';
		//only for sandy if condition is true
		if (message.from != $scope.userName){// not for the cuurnt user it is for the user who has send the data
			$scope.addChat(message);
			$scope.$apply(); //since insidesubscribe closure
		}
	};
	
	$scope.addChat= function(chat){
		$scope.chats.push(chat);//puch chat message in array
	};
	//for james
	$scope.$on('sendingChat',function(event,sentChat){//send chat is nothing but the message (from,to,message)
		chat = angular.copy(sentChat);
		chat.from = 'Me';
		chat.direction = 'outgoing';
		$scope.addChat(chat); //add this chat message to array
	});
		

	
})*/