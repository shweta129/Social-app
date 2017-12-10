/**
 * Job Service
 */

app.factory('JobService',function($http){
	var jobService={}
	
			jobService.addJob=function(job){
		         return $http.post("http://localhost:8080/SocialAppMRest/savejob",job);
	        }
	
	        jobService.getAllJobs=function(){
	        	return $http.get("http://localhost:8080/SocialAppMRest/alljobs")
	        }
	
	        jobService.getJob=function(jobId){
	        	return $http.get("http://localhost:8080/SocialAppMRest/getjob"+jobId)
	        }
	
			return jobService;
	
	
})