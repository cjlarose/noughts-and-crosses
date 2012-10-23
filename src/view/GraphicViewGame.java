package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.Controller;
import model.Game;
import model.Player;

public class GraphicViewGame extends JPanel implements Observer {
	
	private Graphics2D g2;
	private Game game;
	private Controller c;
	
	public GraphicViewGame(Game g) {
		setSize(400,400);
		setLocation(0,0);
		addMouseListener(new MouseClickListener());
		this.game = g;
		update(g, null);
	}

	@Override
	public void update(Observable game, Object arg) {
		if(game == null)
			return;
		
		char[][] matrix = ((Game) game).toMatrix();
		
		for(int i = 0; i < 9; i++) {
			if(matrix[i][i] == 'X')
				drawX(i,i);
			else if(matrix[i][i] == 'O')
				drawO(i,i);
		}
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D)g;
		g2.drawRect(0,0,300,300);
		g2.drawRect(0, 100, 300, 100);
		g2.drawRect(100, 0, 100, 300);
	}
	
	private void drawX(int i, int j) {
		g2.drawString("X", j*100, i*100);
	}
	
	private void drawO(int i, int j) {
		g2.drawString("O", j*100, i*100);
	}
	
	private class MouseClickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {	
			int x = arg0.getX();
			int y = arg0.getY();
			
			int i = x / 100;
			int j = y / 100;
			
			System.out.println(i + " " + j);
			
			try { 
				//makeMove(null, i, j);
			}
			catch(Exception e) {
				System.out.println("Can't move since we aren't playing an actual game.");
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
		
	}
}
