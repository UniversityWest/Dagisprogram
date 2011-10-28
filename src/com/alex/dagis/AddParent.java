package com.alex.dagis;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.alex.dagis.data.DataSourceCommitException;



public class AddParent extends JFrame {
	private Parent mParent; // Current Parent
	/**
	 * 
	 */
	JLabel lParentName;
	JTextField tParentName;
	JLabel lSSN;
	JTextField tSSN;
	
	JButton btnSave;
	private JTextField tParentFirstName;
	private JTextField tParentLastName;
	private JLabel lParentLastName;
	private JLabel lParentFirstName;
	private static final long serialVersionUID = -510946755390468478L;
	
	/**
	 * Gets the result of the add Parent operation
	 * @return
	 */
	public Parent getResult()
	{
		return mParent;
	}
	public AddParent(){
	
		createControls();
	}
	/**
	 * Instansiating this form for editing an Parent
	 * @param editing
	 */
	public AddParent(Parent editing)
	{
		mParent=editing;
		createControls();
		
		// Update the controls for the Parent
		if(mParent != null)
		{
			lParentFirstName.setText(mParent.getFirstName());
			lParentLastName.setText(mParent.getSurName());
		}
	}
	
	/***
	 * Saves the Parent into the RAM
	 */
	protected void save()
	{
		/**
		 * If the mParent is null,
		 * create an new Parent,
		 * otherwise modify an existing one
		 */
		try
		{
			if(mParent == null)
			{
				mParent = new Parent();
				
				
				
				// Add the Parent to the list
				Dagis.dataSource.addParent(mParent);
				
					
				
				
			}
			// Try assert the Parent for it's age, but if age is more than the allowed one (seven year),
			// the Parent is not eligible for kindergarten and should thereof warn the user and stop
			// the operation
			
			mParent.setFirstName(tParentFirstName.getText());
			mParent.setSurName(tParentLastName.getText());
			
			Dagis.dataSource.commit();
			
			// Close the form

			processWindowEvent( new WindowEvent( this, WindowEvent.WINDOW_CLOSING) );
		}
		catch (DataSourceCommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fel uppstod vid skrivning, felet var "+e.getMessage());
		
		} 
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			
		}
	
	}
	/****
	 * Creates the controls
	 */
	protected void createControls()
	{
		GridLayout gl = new GridLayout(4,1);
		// Set layout
		setLayout(gl);
		gl.setVgap(33);
		gl.setHgap(33);
		setSize(640,480);
		// Create controls
		lParentFirstName = new JLabel("Förnamn");
		tParentFirstName = new JTextField();
		lParentLastName = new JLabel("Efternamn");
		tParentLastName = new JTextField();
		lSSN = new JLabel("Personnummer");
		tSSN = new JTextField();
		
		btnSave = new JButton("Spara/Ändra");
		// Append to the box
		add(lParentFirstName);
		add(tParentFirstName);
		add(lParentLastName);
		add(tParentLastName);
		add(lSSN);
		add(tSSN);
		
		add(btnSave);
		btnSave.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				save();
			}
		});
	}
}
