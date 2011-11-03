package com.alex.dagis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.alex.dagis.data.DataSourceCommitException;

public class MainView extends JFrame { 
	private JList<Kindergarten> mList;
	private List<Kindergarten> mGroups;
	private class KGDataModel implements ListModel<Kindergarten>{
		
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
		public Kindergarten getElementAt(int arg0) {
			// TODO Auto-generated method stub
			return mGroups.get(arg0);
		}
		
		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	ClassMenu mClassMenu;
	KGEditor mKG ;
	public MainView(){
		setLayout(new BorderLayout());
		JPanel panel = new JPanel(new GridLayout(1,1));
		add(panel,BorderLayout.CENTER);
		JPanel btnPanel = new JPanel(new FlowLayout());
		add(btnPanel,BorderLayout.SOUTH);
		btnPanel.setSize(new Dimension(320,28));
		// Create buttons
		JButton listChildren = new JButton("Visa klasser för enhet");
		btnPanel.add(listChildren);
		JButton addClass = new JButton("Ny förskoleenhet");
		btnPanel.add(addClass);
		// Add onlclick
		addClass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Add add event here
			}
		});
		JButton editClass = new JButton("Redigera enhet");
		btnPanel.add(editClass);
		JButton removeClass = new JButton("Ta bort enhet");
		btnPanel.add(removeClass); 
		mList = new JList<Kindergarten>();
		
		JButton btnParents = new JButton("Föräldrar");
		btnPanel.add(btnParents);
		
		btnParents.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ParentList pl = new ParentList();
				pl.addWindowListener(new WindowListener() {
					
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
						Parent p = ((AddParent)e.getSource()).getResult();
						
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				pl.show();
			}
		});
		// Bind the list to the datasource
		mGroups = Dagis.dataSource.getKindergartens();
		
		// Bind events to the button
		mList.setModel(this.new KGDataModel());
		JScrollPane jsp = new JScrollPane(mList);
		panel.add(jsp);
		// EVent listener for adding enhet
		removeClass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Dagis.dataSource.removeKindergarten(mList.getSelectedValue());
				try {
					Dagis.dataSource.commit();
				} catch (DataSourceCommitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mList.setModel(new KGDataModel());
			
			}
		});
		editClass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mKG = new KGEditor(mList.getSelectedValue());
				mKG.show();
mKG.addWindowListener(new WindowListener() {
					
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
						Kindergarten kt = mKG.getResult();
						
						// Save the changes
						try {
							Dagis.dataSource.commit();
						} catch (DataSourceCommitException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						mList.setModel(MainView.this.new KGDataModel());
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
		
		addClass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mKG = new KGEditor();
				mKG.addWindowListener(new WindowListener() {
					
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
						Kindergarten kt = mKG.getResult();
						Dagis.dataSource.addKindergarten(kt);
						
						// Save the changes
						try {
							Dagis.dataSource.commit();
						} catch (DataSourceCommitException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						mList.setModel(MainView.this.new KGDataModel());
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				mKG.show();
			}
		});
		listChildren.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mClassMenu = new ClassMenu(mList.getSelectedValue());
				mClassMenu.show();
			}
		});
	
		setSize(new Dimension(640,480));
		
	}
}
