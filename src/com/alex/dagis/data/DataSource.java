package com.alex.dagis.data;

import java.util.ArrayList;
import java.util.List;

import com.alex.dagis.Child;
import com.alex.dagis.Group;
import com.alex.dagis.Kindergarten;
import com.alex.dagis.Parent;

/*****
 * An IDataProvider interface supplies the 
 * the program with information from loading, updating and inserting data.
 * @author Alexander
 *
 */
public interface DataSource {
	/**
	 * Gets all the kindergartens from the datasource
	 * @return
	 */
	public List<Kindergarten> getKindergartens();
	/**
	 * Returns an list of groups based on the query
	 * @param query
	 * @return
	 */
	public List<Group> getGroups(String... query);

	
	/**
	 * Adds an group to the list
	 * @param group
	 */
	public void addGroup(Kindergarten kg, Group group);
	
	/***
	 * Removes an group from the list
	 * @param group
	 */
	public void removeGroup(Group group);
	
	/**
	 * Adds an kindergarten to the system
	 * @param obj
	 */
	public void addKindergarten(Kindergarten obj);
	/**
	 * Removes an kindergarten from the system
	 * @param obj
	 */
	public void removeKindergarten(Kindergarten obj);
	/**
	 * Gets an list of kindergarten by the query string
	 */
	public List<Kindergarten> getKindergarten(String... query);
	
	/**
	 * Gets an parent by the specified ID
	 * @param ssn
	 * @return
	 */
	public Parent getParentById(int ssn);
	
	/**
	 * Get group by id
	 * @param id
	 * @return
	 */
	public Group getGroupById(int id);
	
	/**
	 * Get groups by kindergarten
	 * @param source
	 * @return
	 */
	public List<Group> getGroupsByKindergarten(Kindergarten source);
	
	/**
	 * Get children by group
	 * @param group
	 * @return
	 */
	public List<Child> getChildrenByGroup(Group group);
	/**
	 * Gets an list of parents
	 * @param parent
	 * @return
	 */
	public List<Parent> getParents(String... query);
	/***
	 * Gets the list of children
	 * @param filter A filter query for some providers
	 * @return
	 */
	public List<Child> getChildren(String... filter);
	/**
	 * Adds an child from the datasource
	 * @param child
	 */
	public void addChild(Group g, Child child);
	/***
	 * Removes an child from the datasource
	 * @param child
	 */
	public void removeChild(Child child);
	
	/**
	 * Adds an parent from the datasource
	 * @param Parent
	 */
	public void addParent(Parent child);
	/***
	 * Removes an child from the datasource
	 * @param parent Removes an parent
	 */
	public void removeParent(Parent child);
	
	/***
	 * Commits the data to the source
	 * @throws DataSourceCommitException if the data cannot be loaded
	 */
	public void commit() throws DataSourceCommitException; 
	/**
	 * Load data from the source
	 * @param args parameters passed to the datasource, like SQL, file name etc.
	 */
	public void load(Object... args) throws DataSourceLoadException;
	public Kindergarten getKindergartenById(int readInt);
	
}
