/**
 * friendcontroller
 */
app.controller('FriendController',function($scope,$location,FriendService){
	function getAllSuggestedUsers()
	{
		FriendService.getAllSuggestedUsers().then(function(response){
			$scope.suggestedusers=response.data   //A -(B U C)
		},function(response){
			
		  if(response.status==401)
			  $location.path('/login')
		})
	}
	
	function getAllPendingRequests(){
		FriendService.getAllPendingRequests().then(function(response){
			$scope.pendingRequests=response.data //select * from friend where toId=? status='P'
		},function(response){
			 if(response.status==401)
				  $location.path('/login')
		})
	}
	
	
	function getListOfFriends(){
		FriendService.getListOfFriends().then(function(response){
			$scope.friends=response.data
		},function(response){
			 if(response.status==401)
				  $location.path('/login')
		})
		
	}
	
	
	$scope.addFriendRequest=function(toId){ // v to c ng-click
		FriendService.addFriendRequest(toId).then(function(response){
		alert('Friend request has been send successfully')
		getAllSuggestedUsers()
		},function(response){
			if(response.status==401)
			  $location.path('/login')
		})
	}
	
	//pending updated request - friend object with status 'P'
	//updated status - char [A/D] from panding request.html
	$scope.updatePendingRequest=function(pendingrequest,updatedStatus){
		pendingrequest.status=updatedStatus
		//pendinrequst - ffriend object with status as 'A' / 'D'
		FriendService.updatePendingRequest(pendingrequest).then(function(response){
			getAllPendingRequests()
		},function(response){
			if(response.status==401)
				  $location.path('/login')
		})
	}
	
	
	getAllSuggestedUsers()
	getAllPendingRequests()
	getListOfFriends()
})