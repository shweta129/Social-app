/**
 * BlogService
 */
app.factory('BlogService',function($http){
	var blogService={}
	
	var BASE_URL="http://localhost:8080/SocialAppMRest"
	blogService.saveBlog=function(blog){
		return $http.post(BASE_URL + "/saveblog",blog)
	}
	
	blogService.getBlogsApproved=function(){
		return $http.get(BASE_URL + "/getblogs"+1)
	}
	
	blogService.getBlogsWaitingForApproval=function(){
		return $http.get(BASE_URL +"/getblogs"+0)
	}
	return blogService;
})