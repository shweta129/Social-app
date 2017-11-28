/**
 * Personservice to make server side calls
 */

app.factory('PersonService',function($http){
	var personService={}
	
	personService.getAllPersons=function(){
		//call server side code by url
		return $http.get("http://localhost:8080/SocialAppMRest/getallpersons")
	}
	
	personService.savePerson=function(person){
		//call server side code by url
		return $http.post("http://localhost:8080/SocialAppMRest/saveperson",person)
	}
	
	personService.deletePerson=function(id){
		return $http['delete']("http://localhost:8080/SocialAppMRest/deleteperson/"+id);
	}
	
	return personService;
	
})