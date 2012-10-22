package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller.GUIListener;
import model.Game;

public class DrawingMouseView extends JPanel implements Observer {
	
	public static void main(String[] args) {
	}
	
	public DrawingMouseView() {
		setSize(400,400);
		setLocation(0,0);
		
		
	}

	@Override
	public void update(Observable game, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawRect(0,0,300,300);
		g2.drawRect(0, 100, 300, 100);
		g2.drawRect(100, 0, 100, 300);
	}
	
	private void drawX(int x, int y) {
		
	}
	
	private void drawO(int x, int y) {
		
	}
}
