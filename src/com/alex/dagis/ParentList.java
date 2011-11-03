package com.alex.dagis;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class ParentList extends JFrame {
	

	private AddParent mParentEditor;
	private Group mGroup;
	private class ParentModel implements TableModel {
		
		// Column in the table for the first name of Parent
		public static final int COLUMN_NAME = 1;
		
		// Column for the year when the Parentren is born
		public static final int COLUMN_BORN_YEAR = 1;
		public static final int COLUMN_SSN = 0;
		public static final int COLUMN_SURNAME = 3;

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			 
		}
		
		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			Parent c = Dagis.dataSource.getParents().get(rowIndex); // get the current Parentf
			switch(columnIndex)
			{
			case COLUMN_SSN:
				return c.getSSN();
			case COLUMN_NAME:
				return c.getName();
			}
			return null;
		}
		
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			// Return count of Parents
			return  Dagis.dataSource.getParents().size();
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			// TODO Auto-generated method stub
			switch(columnIndex)
			{
			case COLUMN_SSN:
				return "Personnummer";
			case COLUMN_NAME:
				return "Namn";
			
			}
			
			return "";
		}
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 3;
		}
		
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			switch(columnIndex)
			{
			case COLUMN_SSN:
				return Integer.class;
			case COLUMN_NAME:
				return String.class;
			
			}
			return Object.class;
		}
		
		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
	}
	JTable table = new JTable();
	/**
	 * 
	 */ 
	private static final long serialVersionUID = 6117124528346991142L;
	public ParentList()
	{
		JPanel container = new JPanel();
		
		JScrollPane jsp = new JScrollPane(table);
		container.setLayout(new BorderLayout());
		
		table.setModel(new ParentModel());
		setPreferredSize(new Dimension(640,480));
		add(jsp);
		setSize(320,320);
		setTitle("Föräldrar registrerade");
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		JButton AddParent = new JButton("Skriv in förälder");
		JButton editParent = new JButton("Redigera förälder");
		editParent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mParentEditor = new AddParent((Parent)table.getValueAt(table.getSelectedRow(), 0));
				mParentEditor.addWindowListener(new WindowListener() {
					
					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						
						table.setModel(new ParentModel());
				
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				mParentEditor.show();
			}
		});
		AddParent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mParentEditor = new AddParent();
				mParentEditor.addWindowListener(new WindowListener() {
					
					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						Parent c = mParentEditor.getResult();
						table.setModel(new ParentModel());
				
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				mParentEditor.show();
			}
		});
		jp.add(AddParent);
		jp.add(editParent);
		jp.setPreferredSize(new Dimension(640,64));
		add(jp,BorderLayout.SOUTH);
		
	}
}
