package com.alex.dagis;

import com.alex.dagis.data.DataEntry;

/**
 * An item is an type of entry, like Organisation, Kindergarden, which not is an human being
 * @author Alexander
 *
 */
public abstract class Item implements   DataEntry{
	
	/**
	 * Default id field for items who havent defined an own, such ssID, UPCs
	 * or another
	 */
	private int mID;
	
	@Override
	public int getID()
	{
		return mID;
	}
	@Override
	public void setID(int id)
	{
		this.mID=id;
	}
	/**
	 * Default constructor, assigns an unique
	 * id by random if the subclasses does not define
	 * it by explicit call.
	 */
	public Item()
	{
		setID((int)(Math.random()*10000));
	}
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 instanceof DataEntry)
		{
			return ((DataEntry)arg0).getID() == getID();
		}
		return super.equals(arg0);
	}
}
