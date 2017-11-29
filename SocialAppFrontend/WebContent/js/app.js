/**
 * Angular module
 */
//1st parameter is module name
//2nd parameter is array of dependent modules[]-->no dependent module
//['ng-route']==>for single page appliction
var app=angular.module('app',['ngRoute'])
app.config(function($routeProvider){
	$routeProvider
	.when('/persons',{
	  templateUrl:'persons.html',//view ng-repeat="p in persons"
	  controller:'PersonCtrl'    //Controller $scope.persons
	
	
	})
	
	.when('/personform',{
		templateUrl:'personform.html',
		controller:'PersonCtrl'
	})
	
	.otherwise({templateUrl:'home.html'})
	
	
})