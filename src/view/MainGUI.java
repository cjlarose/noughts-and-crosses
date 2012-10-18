package view;


import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Game;

import controller.Controller.GUIListener;


public class MainGUI extends JFrame implements GameView {

	private GUIListener gui_listener; 
	private JButton textViewButton;
	private JButton graphicViewButton;
	
	public MainGUI() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Naughts and Crosses");
		this.setSize(400,400);
		this.setLocation(100,100);
		this.setResizable(false);
		this.setVisible(true);
		
		Container content = this.getContentPane();
		content.setLayout(new GridLayout(2, 2, 10, 10));
		
		textViewButton = new JButton("Text View");
		textViewButton.addActionListener(new ChangeViewListener());
		content.add(textViewButton);
		graphicViewButton = new JButton("Graphical View");
		textViewButton.addActionListener(new ChangeViewListener());
		content.add(graphicViewButton);
	}
	
	private class ChangeViewListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource().equals(textViewButton)) {
				//Change to text view
			}
			else if(arg0.getSource().equals(graphicViewButton)) {
				//Change to graphic view
			}
			
		}
		
	}
	
	public void setGlobalListener(GUIListener l) {
		this.gui_listener = l;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
