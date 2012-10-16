package model;

import java.awt.event.ActionListener;

public interface Player {

	/*
	 * Ask a player to respond.  When a response is fired, call e.actionPerformed()
	 */
	public void respond(Game g, ActionListener e);
	
}
