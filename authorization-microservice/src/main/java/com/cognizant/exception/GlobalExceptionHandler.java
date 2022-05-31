package com.cognizant.exception;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handles all the global exceptions
 * 
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handles input validation errors
	 * 
	 * @param MethodArgumentNotValidException ex
	 * @return ResponseEntity with error messages
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorResponse response = new ErrorResponse();
		response.setMessage("Invalid Credentials");
		response.setTimestamp(LocalDateTime.now());

		// Get all validation errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());

		// Add errors to the response map
		response.setFieldErrors(errors);

		return new ResponseEntity<>(response, headers, status);
	}
	
	
//	@ResponseStatus(HttpStatus.UNAUTHORIZED)
//	@ResponseBody
//	@ExceptionHandler(value = AuthenticationException.class)
//	public ResponseModal handleAuthenticationExceptions(AuthenticationException ex, HttpServletResponse response) {
//	    LOGGER.info("Authentication Exception: {}", ex.getMessage());
//	    
//	    return new ResponseModal(HttpStatus.UNAUTHORIZED.value(), "whatever you want");
//	}
	
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleAuthenticationExceptions(
			AuthenticationException ex, HttpServletResponse response) {
		//LOGGER.info("Authentication Exception: {}", ex.getMessage());
		String errorMessage = ex.getMessage();
		ErrorResponse response1 = new ErrorResponse(errorMessage, LocalDateTime.now(), Collections.singletonList(errorMessage));
		return new ResponseEntity<>(response1, HttpStatus.UNAUTHORIZED);
	}


	/**
	 * Handles invalid credentials exception
	 * 
	 * @param invalidCredentialsException
	 * @return Response Entity of custom type Error Response with error message and
	 *         time-stamp
	 */
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorResponse> handlesUserNotFoundException(
			InvalidCredentialsException invalidCredentialsException) {
		String errorMessage = invalidCredentialsException.getMessage();
		ErrorResponse response = new ErrorResponse(errorMessage, LocalDateTime.now(), Collections.singletonList(errorMessage));
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Handles invalid token exceptions
	 * 
	 * @param invalidCredentialsException
	 * @return Response Entity of custom type Error Response with error message and
	 *         time-stamp
	 */
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorResponse> handlesTokenInvalidException(InvalidTokenException invalidTokenException) {
		String errorMessage = invalidTokenException.getMessage();
		ErrorResponse response = new ErrorResponse(errorMessage, LocalDateTime.now(), Collections.singletonList(errorMessage));
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
}
