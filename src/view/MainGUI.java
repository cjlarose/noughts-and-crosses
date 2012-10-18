package view;


import java.util.Observable;

import javax.swing.JFrame;

import model.Game;

import controller.Controller.GUIListener;


public class MainGUI extends JFrame implements GameView {

	private GUIListener gui_listener; 
	
	public void setGlobalListener(GUIListener l) {
		this.gui_listener = l;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
