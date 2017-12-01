/**
 * Angular module
 */
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
	
	
	/*.when('/persons',{
	  templateUrl:'persons.html',//view ng-repeat="p in persons"
	  controller:'PersonCtrl'    //Controller $scope.persons
	
	
	})
	
	
	
	.when('/personform',{
		templateUrl:'personform.html',
		controller:'PersonCtrl'
	})
	
	.when('/getperson/:id',{
		templateUrl:'personform.html',
		controller:'EditPersonController'
	})*/
	
	.otherwise({templateUrl:'views/home.html'})
	
	
})