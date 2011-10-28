package com.alex.dagis;

import java.io.Serializable;

/***
 * An address represents an address to an particular event
 * @author Alexander
 *
 */
public class Address implements Serializable{
	private int zip;
	private String city;
	private String address;
	private String port;
	private String street;
	private String street_no;
	private String person;
	@Override
	public String toString()
	{
		return String.format("%s\n %s %s\n%s %s\n%s %s",person,street,street_no,zip,city);
	}
}
