package com.alex.dagis;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alex.dagis.data.DataEntry;

/***
 * An group of children
 * @author Alexander
 *
 */
public class Group extends Item implements DataEntry, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1072230666087964001L;
	private void writeObject(java.io.ObjectOutputStream out)
	{
		
		try {
			out.writeInt(getID());
			out.writeUTF(getName());
			out.writeInt(getMaximumPlaces());
			out.writeInt(getKindergarten().getID());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void readObject(java.io.ObjectInputStream in)
	{
		
		try {
			setID(in.readInt());
			setName(in.readUTF());
			setMaximumPlaces(in.readInt());
			setKinderGarten(Dagis.dataSource.getKindergartenById(in.readInt()));
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (CountPlacesNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private int mMaxPlaces;
	@Override
	public String toString(){
		return getName() + String.format("(%d platser lediga)",getPlacesLeft());
	}
	private String mName;
	public String getName()
	{
		return mName;
	}
	public void setName(String mName)
	{
		this.mName = mName;
	}
	public int getMaximumPlaces()
	{
		return mMaxPlaces;
	}
	/**
	 * Gets the free places left
	 * @return
	 */
	public int getPlacesLeft()
	{
		return getMaximumPlaces() - getChildren().size();
	}
	private int mKindergartenID;
	public void setKinderGarten(Kindergarten kg)
	{
		mKindergartenID=kg.getID();
	}
	/**
	 * Gets the kindergarten which owns the group
	 * @return
	 */
	public Kindergarten getKindergarten()
	{
		
		for(Kindergarten kg : Dagis.dataSource.getKindergartens())
		{
			if(kg.getID() == mKindergartenID)
				return kg;
		}
		return null;
	}
	/**
	 * Set maximum count of children that can be inserted to the group
	 * @param c
	 * @throws CountPlacesNumberException
	 */
	public void setMaximumPlaces(int c) throws CountPlacesNumberException
	{
		// Places must be an non-negative integer not less than one
		if(c < 1)
		{
			
			throw new CountPlacesNumberException();
			
		}
		mMaxPlaces = c;
	}
	
	public List<Child> getChildren()
	{
		return Dagis.dataSource.getChildrenByGroup(this);
	}
	

}
