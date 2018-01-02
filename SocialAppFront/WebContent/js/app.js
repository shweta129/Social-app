//1st parameter is module name
//2nd parameter is array of dependent modules[]-->no dependent module
//['ng-route']==>for single page appliction

var app=angular.module('app',['ngRoute','ngCookies'])
app.config(function($routeProvider){
	$routeProvider
	
	
	.when('/register',{
		  templateUrl:'views/registrationform.html',   //view ng-repeat="p in persons"
		  controller:'UserController'                  //Controller $scope.user
		
		
		})

	.when('/login',{
	  templateUrl:'views/loginForm.html',     //view ng-repeat="p in persons"
	  controller:'UserController'             //Controller $scope.user
	
	})
	
	.when('/editprofile',{
	  templateUrl:'views/userprofile.html',   //view ng-repeat="p in persons"
	  controller:'UserController'             //Controller $scope.user
	
	})
	
	.when('/addjob',{                    //data is from jobform.html to controller
	  templateUrl:'views/jobform.html',  //view ng-repeat="p in persons"
	  controller:'JobController'         //Controller $scope.user
	
	})

	
	.when('/alljobs',{                    //from controller to view[$scope.person=[]]
	  templateUrl:'views/joblist.html',  //view ng-repeat="p in persons"
	  controller:'JobController'         //Controller $scope.user
	
	})
	
	.when('/getJob/:id',{                    //view to controller
	  templateUrl:'views/profilejob.html',  //view ng-repeat="p in persons"
	  controller:'JobController'         //Controller $scope.user
	
	})
	
	.when('/addblog',{                    //view to controller
	  templateUrl:'views/blogform.html',  //view ng-repeat="p in persons"
	  controller:'BlogPostController'         //Controller $scope.user
	
	})
	
	
	.when('/getblogs',{                    //controller to view
		  templateUrl:'views/blogslist.html',  //view ng-repeat="p in persons"
		  controller:'BlogPostController'         //Controller $scope.user
		
		})
		
		
	.when('/getbloglist',{                    //controller to view
		  templateUrl:'views/blogdetails.html',  //view ng-repeat="p in persons"
		  controller:'BlogPostController'         //Controller $scope.user
		
		})	
		
	.when('/admin/getblog/:id',{                    
	  templateUrl:'views/approvalform.html',  //view ng-repeat="p in persons"
	  controller:'BlogDetailController'         //Controller $scope.user
	
	})	
	
	
	.when('/getblog/:id',{                   
	  templateUrl:'views/blogdetails.html',  //view ng-repeat="p in persons"
	  controller:'BlogDetailController'         //Controller $scope.user
	
	})	
	
	.when('/home',{                   
	  templateUrl:'views/home.html',  //view ng-repeat="p in persons"
	   controller:'HomeController'       
	
	})	
	
	.when('/uploadpic',{                   
	  templateUrl:'views/profilepicture.html',  //view ng-repeat="p in persons"
	   controller:'HomeController'       
	
	})	
	
	.when('/suggestedusers',{                   
	  templateUrl:'views/suggestedusers.html',  //view ng-repeat="p in persons"
	   controller:'FriendController'       
	
	})	
	
	.otherwise('/login',{
	  templateUrl:'views/loginForm.html',     //view ng-repeat="p in persons"
	  controller:'UserController'   })
	
})

app.run(function($rootScope,$cookieStore,UserService,$location){
	/*alert($cookieStore.get('currentUser'))*/
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