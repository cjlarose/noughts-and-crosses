package view;
import java.util.Observer;

import controller.Controller.GUIListener;

public interface GameView extends Observer {

	public void setGlobalListener(GUIListener l);
	
}
