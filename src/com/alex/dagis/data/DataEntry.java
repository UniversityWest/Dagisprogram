package com.alex.dagis.data;

import com.alex.dagis.Parent.TwoParentSameSSNException;

/***
 * An data entry represents an entry from an database or another data source
 * @author Alexander
 *
 */
public interface DataEntry {
	public class MultipleIDItemException extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = -2398612802584379621L;
		
	}
	/***
	 * Gets the unique identifier for the object
	 * @return int the identifier of the object
	 */
	public int getID();
	/**
	 * Sets an id for an particular item
	 * @param id
	 * @throws TwoParentSameSSNException 
	 */
	public void setID(int id) throws MultipleIDItemException;

}
