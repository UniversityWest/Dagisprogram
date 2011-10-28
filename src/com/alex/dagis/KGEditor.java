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
import javax.swing.JTextField;

/**
 * Editor for kindergarten items
 * @author Alexander
 *
 */
public class KGEditor extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9084169770885279944L;
	JLabel lName = new JLabel("Namn på enhet");
	JTextField tName = new JTextField();
	JButton submit = new JButton("Spara");
	Kindergarten mKg;
	public Kindergarten getResult()
	{
		return mKg;
	}
	public KGEditor(Kindergarten c)
	{
		mKg=c;
		addControls();
	}
	public KGEditor(){
		addControls();
	}
	/***
	 * Creates the input system
	 */
	protected void addControls()
	{
		setLayout(new GridLayout(4,1));
		setPreferredSize(new Dimension(320,640));
		add(lName);
		add(tName);
		add(submit);
		// If we load an object, assert it's changes
		if(mKg != null)
		{
			tName.setText(mKg.getName());
		}
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Get an editing object or create an new
				if (mKg == null)
				{
					mKg = new Kindergarten();
					
				}
				mKg.setName(tName.getText());
				
				// Configure the object
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new WindowEvent(KGEditor.this, WindowEvent.WINDOW_CLOSING));
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new WindowEvent(KGEditor.this, WindowEvent.WINDOW_CLOSED));
				
			}
		});
	
	
	}
}
