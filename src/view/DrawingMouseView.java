package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Game;

public class DrawingMouseView extends JPanel implements Observer {
	
	public static void main(String[] args) {
	}
	
	public DrawingMouseView(Game g) {
	}

	@Override
	public void update(Observable game, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

	}
}
