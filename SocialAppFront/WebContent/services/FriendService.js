/**
 * friend service
 */

app.factory('FriendService',function($http){
	var friendService={}
	
	var BASE_URL="http://localhost:8080/SocialAppMRest"
		
	friendService.getAllSuggestedUsers=function(){
		return $http.get(BASE_URL +"/suggestedusers")
	}	
	return friendService;
})