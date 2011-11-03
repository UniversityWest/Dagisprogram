package com.alex.dagis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.alex.dagis.Parent.TwoParentSameSSNException;
import com.alex.dagis.data.DataEntry;
/***
 * An abstract class 'person'
 * @author Alexander
 *
 */
public abstract class Person implements Serializable, DataEntry {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7551256415496039326L;
	
	private int mSSN;
	public int getSSN() {
		return mSSN;
	}
	public void setSSN(int mSSN) {
		this.mSSN = mSSN;
	}
	private String mFirstName;
	public String getFirstName() {
		return mFirstName;
	}
	public void setFirstName(String mFirstName) {
		this.mFirstName = mFirstName;
	}
	private String mSurName;
	
	public String getSurName() {
		return mSurName;
	}
	public void setSurName(String mSurName) {
		this.mSurName = mSurName;
	}
	/**
	 * Gets the complete name
	 * @return
	 */
	public String getName()
	{
		return this.mSurName +", "+this.mFirstName;
	}
	/**
	 * The default string gets the name
	 */
	@Override
	public String toString()
	{
		return getName();
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return mSSN;
	}
	@Override
	public void setID(int id) throws TwoParentSameSSNException {
		// TODO Auto-generated method stub
		this.mSSN=id;
	}
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 instanceof Person)
		{
			return ((Person)arg0).getID() == getID();
		}
		return super.equals(arg0);
	}
	
}
