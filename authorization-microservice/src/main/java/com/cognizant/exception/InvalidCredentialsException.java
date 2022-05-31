/**
 * Class to handle Invalid Credentials given by the user
 *
 */
package com.cognizant.exception;

public class InvalidCredentialsException extends RuntimeException {

	private static final long serialVersionUID = -4836014323607899641L;

	public InvalidCredentialsException(String message) {
		super(message);
	}
}
