/**
 * BlogController
 */

app.controller('BlogPostController',function($scope,BlogService,$location){
	
	$scope.saveBlog=function(){
		BlogService.saveBlog($scope.blog)
		.then(function(response){
			alert('Blog Post added successfully and it is waiting for approval')
			$location.path('home')
		},function(response){
			if(response.status==401){
				$location.path('/login')
			}
			if(response.status==500){
				$scope.error=response.data
			
		}
		})
	}
})