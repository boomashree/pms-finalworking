

/**
 * Class to handle Invalid Credentials given by the user
 *
 */
package com.cognizant.exception;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class InvalidTokenException extends RuntimeException {

	private static final long serialVersionUID = -3700741193887093791L;

	public InvalidTokenException(String message) {
		super(message);
		
	}
	
//	@ExceptionHandler(InvalidTokenException.class)
//	public ResponseEntity<ErrorResponse> handlesTokenInvalidException(InvalidTokenException invalidTokenException) {
//		String errorMessage = invalidTokenException.getMessage();
//		ErrorResponse response = new ErrorResponse(errorMessage, LocalDateTime.now(), Collections.singletonList(errorMessage));
//		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//	}
}
