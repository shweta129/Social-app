/**
 * Angular module
 */
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