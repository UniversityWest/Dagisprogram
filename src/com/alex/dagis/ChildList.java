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


public class ChildList extends JFrame {
	// Column in the table for the first name of child
	public static final int COLUMN_NAME = 0;
	// Column for the year when the children is born
	public static final int COLUMN_BORN_YEAR = 1;

	private AddChild mChildEditor;
	private Group mGroup;
	private class ChildModel implements TableModel {
		
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
			Child c = Dagis.dataSource.getChildrenByGroup(mGroup).get(rowIndex); // get the current childf
			switch(columnIndex)
			{
			case COLUMN_NAME:
				return c;
			case COLUMN_BORN_YEAR:
				return c.year;
			}
			return null;
		}
		
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			// Return count of children
			return  Dagis.dataSource.getChildrenByGroup(mGroup).size();
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			// TODO Auto-generated method stub
			switch(columnIndex)
			{
			case COLUMN_NAME:
				return "Namn";
			case COLUMN_BORN_YEAR:
				return "Födelseår";
			}
			return "";
		}
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 2;
		}
		
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			switch(columnIndex)
			{
			case COLUMN_NAME:
				return String.class;
			case COLUMN_BORN_YEAR:
				return Integer.class;
			}
			return null;
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
	public ChildList(Group g)
	{
		mGroup=g;
		JPanel container = new JPanel();
		
		JScrollPane jsp = new JScrollPane(table);
		container.setLayout(new BorderLayout());
		
		table.setModel(new ChildModel());
		setPreferredSize(new Dimension(640,480));
		add(jsp);
		setSize(320,320);
		setTitle("Barn i klassen" + g.getName());
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		JButton addChild = new JButton("Skriv in barn");
		JButton editChild = new JButton("Redigera barn");
		editChild.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mChildEditor = new AddChild(mGroup, (Child)table.getValueAt(table.getSelectedRow(), 0));
				mChildEditor.addWindowListener(new WindowListener() {
					
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
						Child c = mChildEditor.getResult();
						table.setModel(new ChildModel());
				
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				mChildEditor.show();
			}
		});
		addChild.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mChildEditor = new AddChild(mGroup);
				mChildEditor.addWindowListener(new WindowListener() {
					
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
						Child c = mChildEditor.getResult();
						table.setModel(new ChildModel());
				
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				mChildEditor.show();
			}
		});
		jp.add(addChild);
		jp.add(editChild);
		jp.setPreferredSize(new Dimension(640,64));
		add(jp,BorderLayout.SOUTH);
		
	}
}
