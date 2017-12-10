/**
 * JobController
 */

app.controller('JobController',function($scope,JobService,$location){
	//show details only user click on the perticular person link
	$scope.showDetails=false

	
	// perticular user add the ther job profile
	$scope.addJob=function(){
		JobService.addJob($scope.job).then(
				function(response){
					alert('Job details Posted successfully')
					$location.path('/home')
					
				},function(response){
			
					if(response.status==401){
						if(response.data.code==6)//Access denied
							$location.path('/home')
						$scope.error=response.data
						$location.path('/login')
					}
					
					if(response.status==500){
						$scope.error=response.data
						$location.path('/addjob')
					}
				})
	}
	//fetch the job of perticular user by thr id
	$scope.getJob=function(jobId){
		$scope.showDetails=false
		JobService.getJob(jobId).then(function(response){
			$scope.job=response.data
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
				
			}
		
		})
	}
	
	
	//list of jobs
	function getAllJobs(){
		JobService.getAllJobs().then(function(response){
			$scope.jobs = response.data  //List<Job> in json format
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path("/login")
			}
		})
	}
	getAllJobs()   //function call
})