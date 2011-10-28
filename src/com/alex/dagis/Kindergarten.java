package com.alex.dagis;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alex.dagis.data.DataEntry;

/**
 * An kindergarden is an organisation unit.
 * @author Alexander
 *
 */
public class Kindergarten extends Item implements Serializable, Organisation, DataEntry {
	public static final long serialVersionUID = 6148965375893238743L;
	public Kindergarten()
	{
		this.id = (int)(Math.random()*10000);
	}
	/***
	 * Returns the list of groups
	 * @return
	 */
	
	private void writeObject(java.io.ObjectOutputStream out)
	{
		
		try {
			out.writeInt(id);
			out.writeUTF(name);
			out.writeUTF(address);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void readObject(java.io.ObjectInputStream in)
	{
		
		try {
			setID( in.readInt());
			setName(in.readUTF());
			setAddress(in.readUTF());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private String address = "";
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name = "";
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.id;
	}
	public int getCountPlaces(){
		int count_places = 0;
		for(Group g : getGroups()){
			count_places += g.getPlacesLeft();
		}
		return count_places;		
	}
	private int id;
	public void setID(int id){
		this.id = id;
	}
	@Override
	public String toString()
	{
		return getName() + String.format("(%d platser lediga)",getCountPlaces());
	}
	public List<Group> getGroups()
	{
		return Dagis.dataSource.getGroupsByKindergarten(this);
	}
	
}
