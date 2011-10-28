package com.alex.dagis;

/**
 * Custom exception which indicates that the entered age
 * is too old to be able to attend kindergarten.
 * @author Alexander
 *
 */
public class ChildTooOldException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5712689100880353061L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "A child older than six year should attend school";

	}

	
}