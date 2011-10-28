package com.alex.dagis.data;
/***
 * Exception concerning data source tasks
 * @author Alexander
 *
 */
public class DataSourceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7109612432766278138L;
	private String msg;
	public DataSourceException(String msg)
	{
		this.msg=msg;
	}
	@Override
	public String getMessage()
	{
		return msg;
	}
}
