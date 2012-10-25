package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.Controller;

public class GraphicViewGame extends JPanel implements Observer {

	private Graphics2D g2;
	private Controller c;

	public GraphicViewGame(Controller c) {
		setSize(400, 400);
		setLocation(0, 0);
		addMouseListener(new MouseClickListener());
		this.c = c;
	}

	@Override
	public void update(Observable game, Object arg) {
		if (game == null)
			return;

		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.drawRect(0, 0, 300, 300);
		g2.drawRect(0, 100, 300, 100);
		g2.drawRect(100, 0, 100, 300);
		if (c.getCurrentGame() != null)
			paintHelper(g);
	}

	private void paintHelper(Graphics g) {
		char[][] matrix = c.getCurrentGame().toMatrix();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (matrix[i][j] == 'O') {
					System.out.println("draw O at " + i + j);
					drawO(i, j, g);
				} else if (matrix[i][j] == 'X') {
					System.out.println("draw X at " + i + j);
					drawX(i, j, g);
				}
			}
		}
	}

	private void drawX(int i, int j, Graphics g) {
		Line2D.Double line = new Line2D.Double(j * 100, i * 100, (j + 1) * 100,
				(i + 1) * 100);
		((Graphics2D) g).draw(line);
		line = new Line2D.Double((j + 1) * 100, i * 100, j * 100, (i + 1) * 100);
		((Graphics2D) g).draw(line);
	}

	private void drawO(int i, int j, Graphics g) {
		Ellipse2D.Double circle = new Ellipse2D.Double((j * 100) + 5,
				(i * 100) + 5, 90, 90);
		((Graphics2D) g).draw(circle);
	}

	private class MouseClickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			int x = arg0.getX();
			int y = arg0.getY();

			int j = x / 100;
			int i = y / 100;

			System.out.println(i + " " + j);

			c.makeMove(i, j);
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
