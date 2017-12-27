/**
 * HomeService
 */

app.factory('HomeService',function($http){
	var homeService={}
	var BASE_URL="http://localhost:8080/SocialAppMRest"
		
	homeService.getNotificationNotViewed=function(){
		return $http.get(BASE_URL + "/getnotification/"+0)//select * from notification  where username=? viewed= ?
	}
	
	homeService.getNotificationViewed=function(){
		return $http.get(BASE_URL + "/getnotification/"+1)//select * from notification  where username=? viewed= ?
	}
	
	homeService.updateNotification=function(notificationId){
		return $http.put(BASE_URL + "/updatenotification/"+notificationId)
	}
	
	return homeService;
})