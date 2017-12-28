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
	

	
	
	homeService.getNotificationNotViewedLike=function()
	{
		//select * from notification where username=? and viewed=0
		return $http.get(BASE_URL + "/getnotificationlike/"+ 0);
	}
	
	homeService.getNotificationViewedLike=function()
	{
		//select * from notification where username=? and viewed=1
		return $http.get(BASE_URL + "/getnotificationlike/"+ 1);
	}
	
	homeService.updateNotificationLike=function(notificationId)
	{
		return $http.put(BASE_URL + "/updatenotificationlike/"+notificationId);
	}
	
	
	
	
	
	homeService.getNotificationNotViewedComment=function()
	{
		//select * from notification where username=? and viewed=0
		return $http.get(BASE_URL + "/getnotificationcomment/"+ 0);
	}
	
	homeService.getNotificationViewedComment=function()
	{
		//select * from notification where username=? and viewed=1
		return $http.get(BASE_URL + "/getnotificationcomment/"+ 1);
	}
	
	homeService.updateNotificationComment=function(notificationId)
	{
		return $http.put(BASE_URL + "/updatenotificationcomment/"+notificationId);
	}
	
	return homeService;
})