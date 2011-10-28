package com.alex.dagis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.alex.dagis.data.DataSource;
import com.alex.dagis.data.DataSourceLoadException;
/***
 * Manages children with file from ground.
 * @author Alexander
 *
 */
public class SingleFileProvider implements DataSource {
	public List<Kindergarten> getKindergartens()
	{
		return kindergartens;
	}
	public Parent getParentById(int ssn)
	{
		for(Parent p : parents)
		{
			if(p.getSSN() == ssn)
			{
				return p;
			}
		}
		return null;
	}
	public Parent getOrCreateParentFromID(int ssn)
	{
		Parent targetParent = getParentById(ssn);
		if(targetParent == null)
		{
			Parent parent = new Parent();
			parent.setSSN(ssn);
			parents.add(parent);
			return parent;
		}
		else
		{
			return targetParent;
		}
			
	}
	
	private  ArrayList<Parent> parents = new ArrayList<Parent>();
	/****
	 * { Declarations }1
	 * Info taken från page http://www.i-coding.de/www/en/java/list/sorting.html
	 * 
	 */
	/**
	 * Arraylist of children
	 */
	private ArrayList<Child> children = new ArrayList<Child>();
	private ArrayList<Kindergarten> kindergartens = new ArrayList<Kindergarten>();
	private ArrayList<Group> mGroups = new ArrayList<Group>();
	/****************************
	 * Loads children from the file
	 */
	
	@Override
	public void addChild(Group g, Child child) {
		// TODO Auto-generated method stub
		child.setGroup(g.getID());
		children.add(child);
		
	}

	@Override
	public void removeChild(Child child) {
		// TODO Auto-generated method stub
		children.remove(child);
	}

	@Override
	public void addParent(Parent parent) {
		// TODO Auto-generated method stub
		parents.add(parent);
	}

	@Override
	public void removeParent(Parent parent) {
		// TODO Auto-generated method stub
		parents.remove(parent);
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		// Write parents first
		
	
		
		FileOutputStream fos;
		
		try {
			
		
			
			fos = new FileOutputStream("C:\\database");
			ObjectOutputStream ous = new ObjectOutputStream(fos);
			
			// Write parents --------------------------------------------------------------------------
			
			// write count of parents
			ous.writeInt(parents.size());
			
			// Write all children
			for(Parent p : parents)
			{
				 ous.writeObject(p);
			}
	
		
			// Write children -------------------------------------------------------------------------
			
			// write count of parents
			ous.writeInt(children.size());
			
			// Write all children
			for(Child c : children)
			{
				 ous.writeObject(c);
			}
			
			// Write list of kindergartens -------------------------------------------------------------------------
			
			// write count of parents
			ous.writeInt(kindergartens.size());
			
			// Write all children
			for(Kindergarten c : kindergartens.subList(0, kindergartens.size()))
			{
				 ous.writeObject(c);
			}
			
			// Write list of groups -------------------------------------------------------------------------
			
			// write count of groups
			ous.writeInt(mGroups.size());
			
			// Write all children
			for(Group c : mGroups.subList(0, mGroups.size()))
			{
				 ous.writeObject(c);
			}
						
			
			// Close the stream -----------------------------------------------------------------------
			fos.close();
		}
		catch (NotSerializableException e)
		{
			e.printStackTrace();
		}
		 catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	protected List<Kindergarten> loadKindergartens(ObjectInputStream ois)
	{
		try {
			
			
			// Create an temporary arraylist for new children,
			// and if the operation sucess, replace the ordinary
			// list of children with the new buffer
			ArrayList<Kindergarten> kindergartens = new ArrayList<Kindergarten>();
			
			/***
			 * We use ObjectInputStream for reading data
			 * @from http://download.oracle.com/javase/1.4.2/docs/api/java/io/ObjectInputStream.html
			 */
	
			int countObjects = ois.readInt();		// Count of objects
			for(int i=0; i < countObjects; i++)
			{
				Kindergarten c = (Kindergarten)ois.readObject();
				kindergartens.add(c);
			}
			this.kindergartens=kindergartens;
			// As the operation seems to have been success,
			// replace the previous buffer with the new buffer
			return kindergartens;
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
		return null;
	}
	/***
	 * Loads a list of children.  loadParents should be called prior to loadChildren
	 * since children are connected to parent by their IDs.
	 * @return
	 * 
	 */
	protected List<Child> loadChildren(ObjectInputStream ois) {
		// TODO Auto-generated method stub
		try {
			
			
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
			this.children=children;
			// As the operation seems to have been success,
			// replace the previous buffer with the new buffer
			return children;
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
		return null;
	}
	/***
	 * Loads a list of children.  loadParents should be called prior to loadChildren
	 * since children are connected to parent by their IDs.
	 * @return
	 * 
	 */
	protected List<Child> loadGroups(ObjectInputStream ois) {
		// TODO Auto-generated method stub
		try {
			
			
			// Create an temporary arraylist for new children,
			// and if the operation sucess, replace the ordinary
			// list of children with the new buffer
			ArrayList<Group> groups = new ArrayList<Group>();
			
			/***
			 * We use ObjectInputStream for reading data
			 * @from http://download.oracle.com/javase/1.4.2/docs/api/java/io/ObjectInputStream.html
			 */
	
			int countObjects = ois.readInt();		// Count of objects
			for(int i=0; i < countObjects; i++)
			{
				Group c = (Group)ois.readObject();
				groups.add(c);
			}
			this.mGroups=groups;
			// As the operation seems to have been success,
			// replace the previous buffer with the new buffer
			return children;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			throw ex;
		}
		return null;
	}
	/***
	 * Loads a list of parents.  This method should be called prior to loadChildren
	 * since children are connected to parent by their IDs.
	 * @return
	 * 
	 */
	protected List<Parent> loadParents(ObjectInputStream ois) {
		// TODO Auto-generated method stub
		try {
			
			
			// Create an temporary arraylist for new children,
			// and if the operation sucess, replace the ordinary
			// list of children with the new buffer
			ArrayList<Parent> parents = new ArrayList<Parent>();
			
			/***
			 * We use ObjectInputStream for reading data
			 * @from http://download.oracle.com/javase/1.4.2/docs/api/java/io/ObjectInputStream.html
			 */
	
			int countObjects = ois.readInt();		// Count of objects
			for(int i=0; i < countObjects; i++)
			{
				Parent c = (Parent)ois.readObject();
				parents.add(c);
			}
			this.parents=parents;
			// As the operation seems to have been success,
			// replace the previous buffer with the new buffer
			return parents;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-gdenerated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public void addKindergarten(Kindergarten obj) {
		// TODO Auto-generated method stub
		kindergartens.add(obj);
	}
	@Override
	public void removeKindergarten(Kindergarten obj) {
		// TODO Auto-generated method stub
		kindergartens.remove(obj);
	}
	@Override
	public List<Kindergarten> getKindergarten(String... query) {
		// TODO Auto-generated method stub
		return kindergartens;
	}
	@Override
	public List<Parent> getParents(String... query) {
		// TODO Auto-generated method stub
		return parents;
	}
	@Override
	public List<Child> getChildren(String... filter) {
		// TODO Auto-generated method stub
		return children;
	}
	@Override
	public void load(Object... args) throws DataSourceLoadException {
		// TODO Auto-generated method stub
		File c = new File("C:\\database");
		if(!c.exists()){
			return;
		}
		try{
			FileInputStream fis = new FileInputStream(("C:\\database"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			loadParents(ois); 
			loadChildren(ois);
			loadKindergartens(ois);
			loadGroups(ois);
			ois.close();
			
		}
		catch(FileNotFoundException nof)
		{
			throw new DataSourceLoadException(nof.getMessage());
		}	
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Inläsningsfel. Filen kan vara skadad");
		}
	}
	
	@Override
	public void addGroup(Kindergarten kg, Group group) {
		// TODO Auto-generated method stub
		mGroups.add(group);
		group.setKinderGarten(kg);
	}
	@Override
	public void removeGroup(Group group) {
		// TODO Auto-generated method stub
		mGroups.remove(group);
	}
	@Override
	public List<Group> getGroups(String... query) {
		// TODO Auto-generated method stub
		return mGroups;
	}
	@Override
	public Group getGroupById(int id) {
		// TODO Auto-generated method stub
		for(Group g : mGroups)
		{
			if(g.getID() == id)
			{
				return g;
			}
		}
		return null;
	}
	@Override
	public List<Group> getGroupsByKindergarten(Kindergarten source) {
		// TODO Auto-generated method stub
		ArrayList<Group> groups = new ArrayList<Group>();
		for(Group g : mGroups)
		{
			try{
			if(g.getKindergarten().getID()==(source.getID()))
			{
				groups.add(g);
			}
			}catch(Exception e){
				
			}
		}
		return groups;
	}
	@Override
	public List<Child> getChildrenByGroup(Group group) {
		// TODO Auto-generated method stub
		ArrayList<Child> children = new ArrayList<Child>();
		for(Child c : this.children)
		{
			if(c.getGroup() == group)
			{
				children.add(c);
			}
		}
		return children;
	}
	@Override
	public Kindergarten getKindergartenById(int readInt) {
		// TODO Auto-generated method stub
		for(Kindergarten kg : getKindergartens())
		{
			if(kg.getID() == readInt){
				return kg;
			}
		}
		return null;
	}
	

}
