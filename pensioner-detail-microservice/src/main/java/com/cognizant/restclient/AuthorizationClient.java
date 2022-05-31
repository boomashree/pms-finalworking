package com.cognizant.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authorization-Service")
public interface AuthorizationClient {

	//validating jwt token with authorization microservice
	@GetMapping("/authorize")
	public Boolean authorization(@RequestHeader("Authorization") String token1);

}
