/**
 * chatservice
 */

app.factory('socket',function($rootScope){
	var socket=new SockJS("/SocialAppMRest/chatmodule")
	var stompClient=Stomp.over(socket)
	stompClient.connect('','',function(frame){
		$rootScope.$broadcast('sockconnected',frame)
	});
	return{
		stompClient:stompClient
	}
})


