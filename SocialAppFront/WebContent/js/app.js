//1st parameter is module name
//2nd parameter is array of dependent modules[]-->no dependent module
//['ng-route']==>for single page appliction

var app=angular.module('app',['ngRoute','ngCookies'])
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

app.run(function($rootScope,$cookieStore,UserService,$location){
	if($rootScope.currentUser==undefined)
		$rootScope.currentUser=$cookieStore.get('currentUser')
		
        $rootScope.logout=function(){
		
		/* call middleware logout url->Middleware-remove HttpSession attribute ,update onile status to false
		 * on success-in frontend , remove cookieStore attribute currentUser,delete $rootScope.
		 */
		 
		UserService.logout().then(function(response){
			delete $rootScope.currentUser;
			$cookieStore.remove('currentUser')
			$location.path('/login')
		
		},function(response){
			console.log(response.status)
			$location.path('/login')
		})
	}
})