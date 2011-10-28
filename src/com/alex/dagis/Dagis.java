package com.alex.dagis;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.alex.dagis.data.DataSource;
import com.alex.dagis.data.DataSourceLoadException;


public class Dagis {
	
	/***
	 * Gets an parent with the specified id
	 * @param ssn
	 * @return Parent a parent if found, otherwise null
	 */
	
	public static DataSource dataSource = new SingleFileProvider();
	static Scanner scanner;
	/***
	 * Main class
	 * @param args
	 */
	public static void main(String[] args)
	{
		try {
			dataSource.load("database");
		} catch (DataSourceLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainView m = new MainView();
		m.show();
			
	}
	public static Parent getOrCreateParentFromID(int parentID) {
		// TODO Auto-generated method stub
		Parent target = (dataSource.getParentById(parentID));
		if(target == null)
		{
			target = new Parent(parentID);
			dataSource.addParent(target);
		}
		return target;
		
	}
}
