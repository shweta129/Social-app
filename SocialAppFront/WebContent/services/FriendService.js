/**
 * friend service
 */

app.factory('FriendService',function($http){
	var friendService={}
	
	var BASE_URL="http://localhost:8080/SocialAppMRest"
		
	friendService.getAllSuggestedUsers=function(){
		return $http.get(BASE_URL +"/suggestedusers")
	}
	
	friendService.addFriendRequest=function(toId){
		return $http.post(BASE_URL +"/addfriendrequest/"+toId)
	}
	
	friendService.getAllPendingRequests=function(toId){
		return $http.get(BASE_URL +"/pendingrequests")
	}
	
	friendService.updatePendingRequest=function(pendingrequest){
		return $http.put(BASE_URL +"/updatependingrequests",pendingrequest)
	}
	
	
	friendService.getListOfFriends=function(){
		return $http.get(BASE_URL +"/getfriends")
	}
	return friendService;
})