package com.cognizant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.cognizant.model.User;
import com.cognizant.repository.UserRepository;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
//author booma
public class AuthorizationMicroserviceApplication {

	private UserRepository repository;
	
	
	@Autowired
	public AuthorizationMicroserviceApplication(UserRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	public void initUser() {
		List<User> users = Stream.of(new User(101, "Iftak", "password1"), new User(102, "User2", "password2"),
				new User(103, "Booma", "shree"),new User(104, "Boom", "naveen")

		).collect(Collectors.toList());
		repository.saveAll(users);
	}
//please run :(
	public static void main(String[] args) {
		SpringApplication.run(AuthorizationMicroserviceApplication.class, args);
	}

}
