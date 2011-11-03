package com.alex.dagis;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.alex.dagis.data.DataSourceLoadException;
/***** 
 * Window for selecting an group within an Kindergarten
 * @author Alexander
 *
 */

public class ClassMenu extends JFrame {
	public List<Group> mGroups;
	private class ClassModel implements ListModel<Group>{
		
		@Override
		public void removeListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return mGroups.size();
		}
		
		@Override
		public Group getElementAt(int arg0) {
			// TODO Auto-generated method stub
			return mGroups.get(arg0);
		}
		
		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	GroupEditor mGE;
	JList<Group> mList;
	private Kindergarten mKindergarten;
	ChildList cl;
	/**
	 * 
	 */
	private static final long serialVersionUID = 5237335232850181080L;
	public ClassMenu(Kindergarten kg){
		mKindergarten = kg;
		
		setLayout(new BorderLayout());
		JLabel lbl = new JLabel(String.format("Klasser i '"+kg.getName()+"'"));
		lbl.setPreferredSize(new Dimension(120,20));
		
		add(lbl,BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(1,1));
		add(panel,BorderLayout.CENTER);
		JPanel btnPanel = new JPanel(new FlowLayout());
		add(btnPanel,BorderLayout.SOUTH);
		btnPanel.setSize(new Dimension(320,28));
		// Create buttons
		JButton listChildren = new JButton("Visa klass");
		btnPanel.add(listChildren);
		JButton addClass = new JButton("Ny klass");
		btnPanel.add(addClass);
		JButton editClass = new JButton("Redigera klass");
		btnPanel.add(editClass);
		JButton removeClass = new JButton("Ta bort klass");
		btnPanel.add(removeClass);
		mList = new JList<Group>();
		JScrollPane jsp = new JScrollPane(mList);
		panel.add(jsp);
		
		// Bind the list to the datasource
		mGroups =  kg.getGroups();
		
		// Bind events to the button
		mList.setModel(new ClassModel());
		listChildren.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl = new ChildList(mList.getSelectedValue());
				cl.addWindowListener(new WindowListener() {
					
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
						
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				cl.show();
			}
		});
		editClass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mGE = new  GroupEditor(mKindergarten,mList.getSelectedValue());
				/**
				 * We add an window closed listener so we can show 
				 * when the class closes
				 */
				mGE.addWindowListener(new WindowListener() {
					
					@Override
					public void windowOpened(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowIconified(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeiconified(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeactivated(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosing(WindowEvent arg0) {
						// TODO Auto-generated method stub
			
						
					}
					
					@Override
					public void windowClosed(WindowEvent arg0) {
						// TODO Auto-generated method stub
						mGroups = ClassMenu.this.mKindergarten.getGroups();
						mList.setModel(new ClassModel());
					}
					
					@Override
					public void windowActivated(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				
				// Show the window
				mGE.show();
			}
		});
		
		addClass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mGE = new  GroupEditor(mKindergarten);
				/**
				 * We add an window closed listener so we can show 
				 * when the class closes
				 */
				mGE.addWindowListener(new WindowListener() {
					
					@Override
					public void windowOpened(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowIconified(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeiconified(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeactivated(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosing(WindowEvent arg0) {
						// TODO Auto-generated method stub
			
						
					}
					
					@Override
					public void windowClosed(WindowEvent arg0) {
						// TODO Auto-generated method stub
						mGroups = ClassMenu.this.mKindergarten.getGroups();
						mList.setModel(new ClassModel());
					}
					
					@Override
					public void windowActivated(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				
				// Show the window
				mGE.show();
			}
		});
		
		setSize(new Dimension(640,480));
		
	}
}
