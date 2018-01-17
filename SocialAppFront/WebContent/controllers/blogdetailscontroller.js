/**
 * BlogDetailsController
 */

app.controller('BlogDetailController', function($scope, $location, $routeParams,$rootScope, BlogService) {

	var id = $routeParams.id
    $scope.showComment=false;
	
	
	BlogService.getBlogPost(id).then(function(response) {
	   $scope.blogPost = response.data
	}, function(response) {
           if (response.status==401) {
			$location.path('/login')

		}
	})
	
	
	//select * from blogpostlikes where blogpost_id=? nd user_username=?
	BlogService.userLikes(id).then(function(response){
		if(response.data=='')//user has not yet like the post
			$scope.liked=false
			else
				$scope.liked=true//user has already like the post
				//alert($scope.liked)
				
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
	
	
	
	
	$scope.addComment=function()
	   {
		if($scope.commentText==undefined){
			alert('Please enter comment')
		}
		else
		 BlogService.addComment($scope.commentText,id).then(function(response){
			//alert(response.status)
			 alert('Your comment successfully post')
			$scope.commentText=''//empty string for list comment in order (one by one)
			$scope.blogPost=response.data//BlogPost with list of blogcomment
		},function(ressponse){
			if(response.status==401){
				$location.path('/login')
			}
			if(response.status==500){
				$scope.error=response.data
			}
		})
	}

	$scope.showComments=function(){
		$scope.showComment=!$scope.showComment
	}
})