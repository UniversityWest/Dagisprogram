package com.alex.dagis.data;
/***
 * An data entry represents an entry from an database or another data source
 * @author Alexander
 *
 */
public interface DataEntry {
	/***
	 * Gets the unique identifier for the object
	 * @return int the identifier of the object
	 */
	public int getID();
	/**
	 * Sets an id for an particular item
	 * @param id
	 */
	public void setID(int id);

}
