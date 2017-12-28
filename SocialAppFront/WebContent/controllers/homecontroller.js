/**
 * HomeController
 */
app.controller('HomeController',function($rootScope,$location,HomeService){
	
	function getNotification(){
		
		HomeService.getNotificationNotViewed().then(function(response){
		  $rootScope.notificationNotViewed=response.data//select * from notif.. where username=? and viewed=0
		  $rootScope.notificationNotViewedLength=$rootScope.notificationNotViewed.length
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
		HomeService.getNotificationViewed().then(function(response){
			$rootScope.notificationViewed=response.data//select * from notif.. where username=? and viewed=1
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	
	
	HomeService.getNotificationNotViewedLike().then(function(response) {
		$rootScope.notificationNotViewedLike = response.data // select * from no.where username=? and viewed=0
		$rootScope.notificationNotViewedLikeLength=$rootScope.notificationNotViewedLike.length
	}, function(response) {
		if (response.status == 401) {
			$location.path('/login')
		}
	})

	HomeService.getNotificationViewedLike().then(function(response) // select *
																// from no.where username=? and	 viewed=1
	{
		$rootScope.notificationViewedLike = response.data
		$rootScope.notificationViewedLikeLength=$rootScope.notificationViewedLike.length

	}, function(response) {
		if (response.status == 401) {
			$location.path('/login')
		}
	})
	
	
	HomeService.getNotificationNotViewedComment().then(function(response) {
		$rootScope.notificationNotViewedComment = response.data // select * from
															// no.where
															// username=?
															// and viewed=0
		$rootScope.notificationNotViewedCommentLength=$rootScope.notificationNotViewedComment.length
	}, function(response) {
		if (response.status == 401) {
			$location.path('/login')
		}
	})

	HomeService.getNotificationViewedComment().then(function(response) // select *
																// from no.where username=? and	 viewed=1
	{
		$rootScope.notificationViewedComment = response.data
		$rootScope.notificationViewedCommentLength=$rootScope.notificationViewedComment.length

	}, function(response) {
		if (response.status == 401) {
			$location.path('/login')
		}
	})
			
	

	
	
	getNotification()//calling
	
	
	
	
	
	$rootScope.updateLength=function(){
		$rootScope.notificationNotViewedLength=0
		$rootScope.notificationNotViewedLikeLength=0
		$rootScope.notificationNotViewedCommentLength=0
	}
	
	$rootScope.updateNotification=function(notificationId){
		HomeService.updateNotification(notificationId).then(function(response){
			getNotification()
		},function(response){
			if(response.status==401)
				$location.path('/login')
			
		})
	}
	
	

	$rootScope.updateNotificationLike=function(notificationId)
	{
		HomeService.updateNotificationLike(notificationId).then(function(response){
			getNotification()
			
		},function(response){
			if(response.status==401)
			{
				$location.path('/login')
			}
			
		})
		
		
		
	}
		
		$rootScope.updateNotificationComment=function(notificationId)
	{
		HomeService.updateNotificationComment(notificationId).then(function(response){
			getNotification()
			
		},function(response){
			if(response.status==401)
			{
				$location.path('/login')
			}
			
		})
		
	
	}
	
})