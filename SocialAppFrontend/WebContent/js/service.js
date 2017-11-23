/**
 * Personservice to make server side calls
 */

app.factory('PersonService',function($http){
	var personService={}
	
	personService.getAllPersons=function(){
		//call server side code by url
		return $http.get("http://localhost:8080/SocialAppMRest/getallpersons")
	}
	
	
	return personService;
	
})