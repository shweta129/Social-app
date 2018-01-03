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
	
	$scope.addFriendRequest=function(toId){
		FriendService.addFriendRequest(toId).then(function(response){
		alert('Friend request added successfully')
		getAllSuggestedUsers()
		},function(response){
			if(response.status==401)
			  $location.path('/login')
		})
	}
	
	getAllSuggestedUsers()
})