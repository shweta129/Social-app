//1st parameter is module name
//2nd parameter is array of dependent modules[]-->no dependent module
//['ng-route']==>for single page appliction

var app=angular.module('app',['ngRoute'])
app.config(function($routeProvider){
	$routeProvider
	
	
	.when('/register',{
		  templateUrl:'views/registrationform.html',//view ng-repeat="p in persons"
		  controller:'UserController'    //Controller $scope.persons
		
		
		})
		
	
	
	
	.when('/login',{
	  templateUrl:'views/loginForm.html',//view ng-repeat="p in persons"
	  controller:'UserController'    //Controller $scope.persons
	
	
	})
	
	
	
	
	
	.otherwise({templateUrl:'views/home.html'})
	
	
})