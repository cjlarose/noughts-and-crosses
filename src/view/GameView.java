package view;
import java.util.Observer;

import model.Game;

import controller.Controller.GUIListener;

public interface GameView extends Observer {

	public void setGlobalListener(GUIListener l);
	
	public void setGame(Game g);
	
}
