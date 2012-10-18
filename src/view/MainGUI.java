package view;


import javax.swing.JFrame;

import controller.Controller.GUIListener;


public class MainGUI extends JFrame implements GameView {

	private GUIListener gui_listener; 
	
	public void setGlobalListener(GUIListener l) {
		this.gui_listener = l;
	}

}
