/**
 * BlogDetailsController
 */

app.controller('BlogDetailController', function($scope, $location, $routeParams, BlogService) {

	var id = $routeParams.id

	BlogService.getBlogPost(id).then(function(response) {
		$scope.blogPost = response.data
	}, function(response) {
		if (response.status==401) {
			$location.path('/login')

		}
	})
	
	$scope.updateBlogPost=function(){
		BlogService.updateBlogPost($scope.blogPost,$scope.rejectionReason).then(function(response){
			$location.path('/getblogs')
			},function(response){
				if(response.status==401){
					$location.path('login')
				}
				if(response.status==500){
					alert(response.data)
					$scope.error=response.data
				}
		})
	}

})