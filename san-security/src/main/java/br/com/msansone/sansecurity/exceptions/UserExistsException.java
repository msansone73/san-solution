package br.com.msansone.sansecurity.exceptions;

public class UserExistsException extends Exception {

	public UserExistsException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 3257068400587359017L;

}
