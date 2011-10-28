package com.alex.dagis;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.alex.dagis.ChildTooOldException;
import com.alex.dagis.data.DataSourceCommitException;



public class AddChild extends JFrame {
	private Child mChild; // Current child
	/**
	 * 
	 */
	JLabel lChildName;
	JTextField tChildName;
	JLabel lParentID;
	JTextField tParentID;
	JLabel lAge;
	JTextField tAge;
	JButton btnSave;
	private JTextField tChildFirstName;
	private JTextField tChildLastName;
	private JLabel lChildLastName;
	private JLabel lChildFirstName;
	private static final long serialVersionUID = -510946755390468478L;
	private Group mGroup;
	/**
	 * Gets the result of the add child operation
	 * @return
	 */
	public Child getResult()
	{
		return mChild;
	}
	public AddChild(Group g){
		mGroup=g;
		createControls();
	}
	/**
	 * Instansiating this form for editing an child
	 * @param editing
	 */
	public AddChild(Group g,Child editing)
	{
		mChild=editing;
		mGroup = g;
		createControls();
		
		// Update the controls for the child
		if(mChild != null)
		{
			lParentID.setText(String.valueOf(mChild.getParent().getSSN()));
			lAge.setText(String.valueOf(mChild.getAge()));
			lChildFirstName.setText(mChild.getFirstName());
			lChildLastName.setText(mChild.getSurName());
		}
	}
	/***
	 * Sets the parent here
	 * @param parentID
	 */
	protected void setParent(int parentID)
	{
		// TODO: Add parent insertion here
		if(mChild != null)
		{
			mChild.setParent(parentID);
		}
	}
	/***
	 * Saves the child into the RAM
	 */
	protected void save()
	{
		/**
		 * If the mChild is null,
		 * create an new child,
		 * otherwise modify an existing one
		 */
		try
		{
			if(mChild == null)
			{
				mChild = new Child();
				
				
				
				// Add the child to the list
				Dagis.dataSource.addChild(mGroup,mChild);
				
					
				
				
			}
			// Try assert the child for it's age, but if age is more than the allowed one (seven year),
			// the child is not eligible for kindergarten and should thereof warn the user and stop
			// the operation
			
			mChild.setAge(Integer.valueOf(tAge.getText()));
			mChild.setFirstName(tChildFirstName.getText());
			mChild.setSurName(tChildLastName.getText());
			mChild.setGroup(mGroup.getID());
			
			setParent(Integer.valueOf(tParentID.getText()));
			
			Dagis.dataSource.commit();
			
			// Close the form
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new WindowEvent(AddChild.this, WindowEvent.WINDOW_CLOSING));
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new WindowEvent(AddChild.this, WindowEvent.WINDOW_CLOSED));
			
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
		catch (ChildTooOldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Barnet är för gammalt för att skrivas in på dagis");
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
		GridLayout gl = new GridLayout(6,2);
		// Set layout
		setLayout(gl);
		gl.setVgap(33);
		gl.setHgap(33);
		setSize(640,480);
		// Create controls
		lChildFirstName = new JLabel("Förnamn");
		tChildFirstName = new JTextField();
		lChildLastName = new JLabel("Efternamn");
		tChildLastName = new JTextField();
		lParentID = new JLabel("Förälder-ID");
		tParentID = new JTextField();
		lAge = new JLabel("Ålder");
		tAge = new JTextField();
		btnSave = new JButton("Spara/Ändra");
		// Append to the box
		add(lChildFirstName);
		add(tChildFirstName);
		add(lChildLastName);
		add(tChildLastName);
		add(lParentID);
		add(tParentID);
		add(lAge);
		add(tAge);
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
				AddChild.this.processWindowEvent( new WindowEvent( AddChild.this, WindowEvent.WINDOW_CLOSED) );
			}
		});
	}
}
