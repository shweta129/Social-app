/**
 * UserController
 */

app.controller('UserController',function($scope,UserService,$location){

  $scope.registerUser=function(){ //step 2.register.html page send request to controller 
	console.log($scope.user)
	  UserService.registerUser($scope.user)//step.3
	.then(function(response){
		$location.path('/home')
	},function(response){
		console.log(response.data)
		console.log(response.status)
		$scope.error = response.data //Errorclazz object in JSON
	
	})//step :9
	  
  }


})	