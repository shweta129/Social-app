/**
 * EditPersoncontroller
 * eg:
 * getperson/1
 * getperson/:id
 * 
 */

app.controller('EditPersonController',function($scope,PersonService,$routeParams){
	var pid=$routeParams.id
	
	PersonService.getPerson(pid).then(function(response){
		$scope.person=response.data  //select * from person where id =?
	},function(response){
		console.log(response.status)
	
	})
	
	
	$scope.updatePerson=function(){
		PersonService.updatePerson($scope.person).then(function(response){
		 $scope.person={}
		 alert('Person details updated successfully');
		},function(response){
			
		console.log(response.status)
			
		})
	}
	
	
})