package com.cognizant.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class ResourceNotFound extends RuntimeException {

	private static final long serialVersionUID = 210649836231358204L;
	public ResourceNotFound(String message) {
		super(message);
	//private String message;
	

}}
