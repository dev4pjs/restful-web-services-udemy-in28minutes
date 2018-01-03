package com.example.demo.api.versioning.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningResourceController {

	
	/*
	 * There is no perfect solution on version of API. It totally depends on the application need.
	 * Following factors taken into considering on which among the following 4 approach will be used,
	 * 1) URI Population
	 * 2) Misuse of HTTP Headers
	 * 3) Caching
	 * 4) Can we execute the request on browser
	 * 5) Easy of doing API documentation
	 * 
	 */
	
	
	// URI Versioning ---twitter
	
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1(){
		return new PersonV1("Purujit Saha");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2(){
		return new PersonV2(new Name("Purujit","Saha"));
	}
	
	
	// Param versioning --- Amazon
	
/*	@GetMapping(name="/person/param",params="version=1")
	public PersonV1 getPerson_V1(){
		return new PersonV1("Purujit Saha");
	}
	
	@GetMapping(name="/person/param",params="version=2")
	public PersonV2 getPerson_V2(){
	return new PersonV2(new Name("Purujit","Saha"));
	}*/
	
	// header versioning --- Microsoft
/*	@GetMapping(value="/person/header",headers="X-API-VERSION=1")
	public PersonV1 getPersonH1(){
		return new PersonV1("Purujit Saha");
	}
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=2")
	public PersonV2 getPersonH2(){
		return new PersonV2(new Name("Purujit","Saha"));
	}
*/	
	
	//Accept Header / Media Type versioning  ----Github
/*	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v1+json")
	public PersonV1 getPersonProduces1(){
		return new PersonV1("Purujit Saha");
	}
	
	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v2+json")
	public PersonV2 getPersonProduces2(){
		return new PersonV2(new Name("Purujit","Saha"));
	}
*/	
	
}
