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
	
	//select * from blogpostlikes where blogpost_id=? nd user_username=?
	BlogService.userLikes($scope.blogPost).then(function(response){
		if(response.data=='')//user has not yet like the post
			$scope.liked=false
			else
				$scope.liked=true//user has already like the post
				alert($scope.liked)
				
	},function(response){
		if (response.status==401) {
			$location.path('/login')

		}
	})
	
	$scope.showRejectionTxt=function(val){
		$scope.isRejected=val
	}
	
	$scope.updateBlogPost=function(){
		BlogService.updateBlogPost($scope.blogPost,$scope.rejectionReason).then(function(response){
			$location.path('/getblogs')
			},function(response){
				if(response.status==401){
					$location.path('/login')
				}
				if(response.status==500){
					alert(response.data)
					$scope.error=response.data
				}
		})
	}
	
	$scope.updateLikes=function(){
		BlogService.updateLikes($scope.blogPost).then(function(response){
			//blogdetails.html,
			//update likes,change the icon color
			$scope.blogPost=response.data;
			$scope.liked=$scope.liked;
		},function(response){
			if(response.status==401){
				$location.path('/login')
			}
		
		})
	}
	
	

})