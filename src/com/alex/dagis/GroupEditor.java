package com.alex.dagis;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.alex.dagis.data.DataSourceCommitException;

public class GroupEditor extends JFrame {


	private static final long serialVersionUID = 791102851530412939L;
	JButton btnSubmit = new JButton("Submit");

	JLabel lName = new JLabel("Namn på grupp:");
	JTextField tName = new JTextField();
	JLabel lMaxPlaces = new JLabel("Max antal platser");
	JTextField tMaxPlaces = new JTextField();
	private Group mGroup;
	/**
	 * Gets the group that are created with the window
	 * @return
	 */
	public Group getResult()
	{
		return mGroup;
	}
	private Kindergarten mKg;
	public GroupEditor(Kindergarten kg)
	{
		mKg=kg;
		addControls();
	}
	public GroupEditor(Kindergarten kg, Group group)
	{
		mKg=kg;
		mGroup=group;
		addControls();
	}
	/**
	 * Sets the form
	 */
	protected void addControls()
	{
		// Set layout base
		setLayout(new GridLayout(4,1));
		setSize(new Dimension(320,480));
		// Add controls
		add(lName);
		add(tName);
		add(lMaxPlaces);
		add(tMaxPlaces);
		add(btnSubmit);
		// Assign default values
		if(mGroup != null)
		{
			tName.setText(String.valueOf(mGroup.getName()));
			tMaxPlaces.setText(String.valueOf(mGroup.getMaximumPlaces()));
		}
		// Add event handlers
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					// TODO Auto-generated method stub
					// Create or use existing group
					if(mGroup == null)
					{
						mGroup = new Group();
						Dagis.dataSource.addGroup(mKg,mGroup); // Add group to the datasource
					}
					mGroup.setName(tName.getText());
					JOptionPane.showMessageDialog(null , tMaxPlaces.getText());
					int max_places = Integer.valueOf(tMaxPlaces.getText()) ;
					if(max_places > 0)
					{
						
							mGroup.setMaximumPlaces(max_places);
						
					}
					else
					{
						
					}
					Dagis.dataSource.commit(); 
					
					// Close the window
					Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new WindowEvent(GroupEditor.this, WindowEvent.WINDOW_CLOSING));
					Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new WindowEvent(GroupEditor.this, WindowEvent.WINDOW_CLOSED));
					
				} catch (CountPlacesNumberException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Antalet platser får inte vara mindre än ett");
					e1.printStackTrace();
				} catch (DataSourceCommitException ex) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Det uppstod ett fel vid insättningen av datan");
					ex.printStackTrace();
				} 
			}
		});
	}
}
