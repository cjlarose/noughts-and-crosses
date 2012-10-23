package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Game;

import controller.Controller;

public class TextFieldInputView extends JPanel implements Observer {
	private JPanel game;
	private JTextArea game_view;
	private JButton make_move;
	private JPanel user_input;
	private Controller c;

	public TextFieldInputView() {
		
		this.c = new Controller(this);

		setSize(400, 300);
		setLayout(new BorderLayout(10, 10));
		
		// board container
		game = new JPanel();
		game.setVisible(true);
		game.setLayout(new BorderLayout(10, 10));

		// board.
		game_view = new JTextArea();
		game_view.setText(c.getCurrentGame().toString());
		game_view.setEditable(false);
		game_view.setFocusable(false);

		Font monospace = new Font("Monospaced", Font.PLAIN, 30);
		
		game_view.setFont(monospace);
		game.add(game_view, "Center");
		
		this.user_input = new ControlsContainer();
		
		add(game, "North");
		add(user_input, "Center");
		
	}
	
	public class ControlsContainer extends JPanel {
		
		private JPanel buttons;
		private JLabel row;
		private JTextField rowInput;
		private JLabel col;
		private JTextField colInput;
		
		public ControlsContainer() {
			// controls container
			this.setVisible(true);
			this.setLayout(new FlowLayout());
			
			// row
			row = new JLabel("Row:");
			rowInput = new JTextField();
			rowInput.setPreferredSize(new Dimension(30, 20));
			this.add(row);
			this.add(rowInput);
			
			// column
			col = new JLabel("Column:");
			colInput = new JTextField();
			colInput.setPreferredSize(new Dimension(30, 20));
			this.add(col);
			this.add(colInput);
			
			// Make move button
			buttons = new JPanel();
			buttons.setVisible(true);
			make_move = new JButton("Make your move");
			make_move.addActionListener(new MoveButtonListener());
			buttons.setLayout(new BorderLayout(10, 10));
			buttons.add(make_move, "Center");
			this.add(buttons);
		}
		
		public class MoveButtonListener implements ActionListener {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked");
				int i = Integer.parseInt(colInput.getText());
				int j = Integer.parseInt(rowInput.getText());
				c.makeMove(i, j);
			}
			
		}
	}

	//
	@Override
	public void update(Observable obj, Object arg1) {
		Game g = (Game) obj;
		if (this.game_view != null)
			this.game_view.setText(g.toString());
	}

}
