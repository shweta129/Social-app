/**
 * PersonController [Model to view]
 */

app.controller('PersonCtrl',function($scope,PersonService){
	function getAllPersons(){
		
		 $scope.persons=PersonService.getAllPersons().then(function(response){
			 $scope.persons=response.data
			 console.log('status code is' + response.status)
			 console.log('Data is'+ response.data)
		  },function(response){
			 console.log('Status code is' + response.status)
			 console.log('Data for error' + response.data)
			
			})	
		
	}
	
	
	 $scope.savePerson=function(){
		 console.log($scope.person)
		 PersonService.savePerson($scope.person).then(function(response){
		 console.log(response.data)
		 console.log(response.status)
		 $scope.person={}
		 alert("Person Details Inserted Successfully")
		 $scope.result="Person Details Inserted Successfully"
			 getAllPersons()
	 },function(response){
			console.log(response.status) 
			
		 })
		 
	 }
	 
	 $scope.deletePerson=function(id){
		 PersonService.deletePerson(id).then(function(response){
			$scope.persons=response.data;
			console.log(response.status)
		 },function(response){
			 console.log(response.status)
		 
		 })
		 
	 }
		 
		getAllPersons() 
		 

})