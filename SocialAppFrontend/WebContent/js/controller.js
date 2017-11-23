/**
 * PersonController [Model to view]
 */

app.controller('PersonCtrl',function($scope,PersonService){
	$scope.persons=PersonService.getAllPersons().then(function(response){
	  $scope.persons=response.data
	  console.log('status code is' + response.status)
	  console.log('Data is'+ response.data)
	},function(response){
		console.log('Status code is' +response.status)
	
	
	})
	
	
	
	
})