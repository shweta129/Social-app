/**
 * UserService
 */

app.factory('UserService',function($http){
  var BASE_URL="http://localhost:8080/SocialAppMRest/"
	
  var userService={}
  
  userService.registerUser = function(user){
	  // http://localhost:8080/SocialAppMRest/  /registeruser
	  //Base_URL                               + "registeruser"
	 
	  return $http.post(BASE_URL + "/registeruser",user) //step:4 
  }
  
  userService.login = function(user){
	  
	  return $http.post(BASE_URL + "/login",user)
  }
  
  userService.logout=function(){
	  return $http.get(BASE_URL + "/logout")
  }

  return userService;
  
})

