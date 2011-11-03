package com.alex.dagis;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.alex.dagis.Parent.TwoParentSameSSNException;
import com.alex.dagis.data.DataEntry;


/**
 * Class for an child at kindergarden
 * @author Alex
 * { Declarations }
 * Info taken från page http://www.i-coding.de/www/en/java/list/sorting.html
 * 
 *
 */ 
public class Child extends Person implements Serializable, Comparable<Child>, DataEntry
{
	public class NoParentException extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = -4294792718438582700L;

		@Override
		public String getMessage() {
			// TODO Auto-generated method stub
			return "The ID does not match any parent registred in the system";
		}
		
	}
	public static final long serivalVersionUID = -7551256415496039326L;
	private int mParentID;
	/**
	 * Writes the object to an input stream
	 * @param in
	 * @throws ChildTooOldException
	 */
	
	private void writeObject(java.io.ObjectOutputStream out)
	{
		int i = 3;
		try {
			out.writeInt(getID());
			out.writeUTF(getFirstName());
			out.writeUTF(getSurName());
			out.writeInt(getAge());
			out.writeInt(mGroupID);
			out.writeInt(mParentID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Reads the object from an input stream
	 * @param in
	 * @throws ChildTooOldException
	 */
	private void readObject(java.io.ObjectInputStream in) 
	{
		
		try {
			setID(in.readInt());
			setFirstName(in.readUTF());
			setSurName(in.readUTF());
			setAge(in.readInt());
			setGroup(in.readInt());
			int parentID = in.readInt();
			if(parentID > 0)
			{
				try {
					setParent(parentID);
				} catch (NoParentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ChildTooOldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwoParentSameSSNException e) {
			JOptionPane.showMessageDialog(null, "Två barn kan inte ha samma ID");
		}
		
	}

	private int mSSN;
	/**
	 * Creates an new child
	 */
	public Child()
	{
		mSSN = (int)Math.random();
	}
	public static void loadFile()
	{
		try {
			FileInputStream fis = new FileInputStream(("databas"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			// Create an temporary arraylist for new children,
			// and if the operation sucess, replace the ordinary
			// list of children with the new buffer
			ArrayList<Child> children = new ArrayList<Child>();
			
			/***
			 * We use ObjectInputStream for reading data
			 * @from http://download.oracle.com/javase/1.4.2/docs/api/java/io/ObjectInputStream.html
			 */
	
			int countObjects = ois.readInt();		// Count of objects
			for(int i=0; i < countObjects; i++)
			{
				Child c = (Child)ois.readObject();
				children.add(c);
			}
			
			// As the operation seems to have been success,
			// replace the previous buffer with the new buffer
			Child.children = children;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/***
	 * Prints the object to the stream
	 * @param stream
	 * @throws IOException
	 */
	
 /**
	 * 
	 */
	private static final long serialVersionUID = 7288628473455905314L;
	private Parent mParent;
	public Parent getParent(){
		try{
			return  Dagis.dataSource.getParentById(mParentID);
		}catch(Exception e){
			return null;
		}
	}
	public void setParent(int id) throws NoParentException {
		// Check if the parent exists
		for(Parent p : Dagis.dataSource.getParents()){
			if(id == p.getSSN()){
				this.mParentID = id;
			}
		}
		// Otherwise throw an new exception
		throw new NoParentException();
	}
	public static ArrayList<Child> children = new ArrayList<Child>();
	
	/**
	 * Social security number
	 */
	public int year;
	// public int ssn;
	/**
	 * Gets the childrens age
	 * @return
	 */
	public int getAge()
	{
		Date c = new java.util.Date();
		
		int cyear = c.getYear()+1900;
		return cyear - year;
	}
	/**
	 * Sets the child's age
	 * @param age the age of the child
	 * @throws ChildTooOldException 
	 */
	public void setAge(int age) throws ChildTooOldException
	{
		/***
		 * Throw an exception if child is beyond 7 years,
		 * because no child older than that should be 
		 * on school!
		 */
		if(age > 7 )
		{
			
				throw new ChildTooOldException();
			
		}
		Date c = new java.util.Date();
		
		
		
		year = (c.getYear()+1900) - age;
		
	}
	public Group getGroup()
	{
		return Dagis.dataSource.getGroupById(mGroupID);
	}
	private Group mGroup;
	private int mGroupID;
	public void setGroup(Group g)
	{

				this.mGroupID=g.getID();
				return;
			
		
	}
	public void setGroup(int groupID)
	{

				this.mGroupID=groupID;
				return;
			
		
	}
	/***
	 * Compares the child by it's name
	 */
	@Override
	public int compareTo(Child arg0) {
		// TODO Auto-generated method stub
		
		return year -  arg0.year;
	}
	
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return mSSN;
	}
	
}