package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
	private JPanel user_input;
	private JPanel buttons;
	private JLabel row;
	private JTextField rowInput;
	private JLabel col;
	private JTextField colInput;
	private JButton make_move;
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

		// controls container
		user_input = new JPanel();
		user_input.setVisible(true);
		user_input.setLayout(new GridLayout(2, 2));
		
		// row
		row = new JLabel("Row:");
		rowInput = new JTextField("");
		user_input.add(row);
		user_input.add(rowInput);
		
		// column
		col = new JLabel("Column:");
		colInput = new JTextField("");
		user_input.add(col);
		user_input.add(colInput);
		
		// Make move button
		buttons = new JPanel();
		buttons.setVisible(true);
		make_move = new JButton("Make your move");
		make_move.addActionListener(new MoveButtonListener());
		buttons.setLayout(new BorderLayout(10, 10));
		buttons.add(make_move, "Center");
		
		add(game, "North");
		add(user_input, "Center");
		add(buttons, "South");
		
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

	//
	@Override
	public void update(Observable obj, Object arg1) {
		Game g = (Game) obj;
		if (this.game_view != null)
			this.game_view.setText(g.toString());
	}

}
