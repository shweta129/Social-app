/**
 * UserController
 */

app.controller('UserController',function($scope,UserService,$location,$rootScope,$cookieStore){

	//Only for edit ,  this statement wil get executed
	//it will not execute for reistrtion
	if($rootScope.currentUser!=undefined){//Fetch user details
	 UserService.getUser().then(function(response){
		 $scope.user=response.data //User Object
	 },function(response){//401
		 if(response.status==401){
			 $location.path('/login')
		 }
		 
	 })	
		
	}
	
	
	
  $scope.registerUser=function(){ //step 2.register.html page send request to controller 
	console.log($scope.user)
	  UserService.registerUser($scope.user)//step.3
	.then(function(response){
		$location.path('/login')
	},function(response){
		console.log(response.data)
		console.log(response.status)
		$scope.error = response.data //Errorclazz object in JSON
	
	})//step :9
	  
  }

  
  $scope.login=function(){
  UserService.login($scope.user).then(function(response){//200,user
	  $rootScope.currentUser=response.data
	 $cookieStore.put('currentUser',response.data)
		  $location.path('/home')
	  },function(response){
		  if(response.status==401){//400.. errorclazz in json format
			  $scope.error=response.data
			  $location.path('/login')
		  }
		  
	  })
  }

  
  $scope.editUserProfile=function(){
	  UserService.editUserProfile($scope.user).then(function(response){
		  alert('update User Successfully')
		  $location.path('/home')
	  },function(response){//401,500
		  if(response.status==401){
			  $location.path('/login')
		  if(response.status==500){
			  $scope.error=response.data//Errorclazz
			  $location.path('/editprofile')
		  }
		  }
		  
	  
	  })
  }
  
  
})	