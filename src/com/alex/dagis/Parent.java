package com.alex.dagis;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.alex.dagis.data.DataEntry;

/***
 * An parent to a child
 * @author Alex
 *
 */
public class Parent extends Person implements Serializable, DataEntry,Comparable<Parent>{
	public class TwoParentSameSSNException extends MultipleIDItemException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5472405862467951113L;

		@Override
		public String getMessage() {
			// TODO Auto-generated method stub
			return "Två föräldrar kan inte ha samma personnummer";
		}
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -7551256415496039326L;

	private void writeObject(java.io.ObjectOutputStream out)
	{
		
		try {
			out.writeInt(getID());
			out.writeUTF(getFirstName());
			out.writeUTF(getSurName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void readObject(java.io.ObjectInputStream in)
	{
		
		try {
			setID(in.readInt());
			setFirstName(in.readUTF());
			setSurName(in.readUTF());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwoParentSameSSNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	public Parent()
	{
		
	}
	
	public Parent(int ssn) throws TwoParentSameSSNException
	{
		setID(ssn);
	}
	
	@Override
	public int compareTo(Parent o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());
	}
	/**
	 * Loads the list of parents into the memory
	 */
	public static void loadFile()
	{
		
	}
	
	
	private int mSSN;
	private String mAddress;
	
	public String getAddress() {
		return mAddress;
	}
	public void setAddress(String mAddress) {
		this.mAddress = mAddress;
	}
	public int getSSN() {
		return mSSN;
	}
	public void setSSN(int mSSN) {
		this.mSSN = mSSN;
	}
	private ArrayList<Child> mChildren = new ArrayList<Child>();
	public ArrayList<Child> getChildren()
	{
		return mChildren;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return mSSN;
	}
	@Override
	public void setID(int id) throws TwoParentSameSSNException {
		// TODO Auto-generated method stub
		// Check if id is already present
		for(Parent parent : Dagis.dataSource.getParents()){
			if(parent.getSSN() == id){
				throw new  TwoParentSameSSNException();
			}
		}
		mSSN=id;
	}
}
