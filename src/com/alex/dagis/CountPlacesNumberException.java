package com.alex.dagis;
/**
 * Exception for asserting wrong number of places on an certain Group
 * @author Alexander
 *
 */
public class CountPlacesNumberException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -675252636232637549L;
	@Override
	public String getMessage()
	{
		return "The count of places must be an positive number not less than zero";
	}
}
