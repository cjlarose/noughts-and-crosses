package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Controller.GUIListener;

import model.Game;

public class TextFieldInputView extends JPanel implements Observer {
	private Game current_game;
	private JPanel game;
	private JTextArea game_view;
	private JPanel user_input;
	private JPanel buttons;
	private JLabel row;
	private JTextField rowInput;
	private JLabel col;
	private JTextField colInput;
	private JButton make_move;

	public TextFieldInputView() {
		// this.current_game = g;

		setSize(500, 800);

		//setLocation(10, 10);
		setLayout(new BorderLayout(10, 10));
		//setName("Naughts and Crosses");
		
		game = new JPanel();
		game.setVisible(true);
		game.setLayout(new BorderLayout(10, 10));

		game_view = new JTextArea();
		game_view.setEditable(false);
		game_view.setFocusable(false);

		Font monospace = new Font("Monospaced", Font.PLAIN, 30);
		
		game_view.setFont(monospace);
		// game_view.setText(current_game.toString());
		game.add(game_view, "Center");

		user_input = new JPanel();
		user_input.setVisible(true);
		user_input.setLayout(new GridLayout(2, 2));
		row = new JLabel("Row:");
		rowInput = new JTextField("");
		user_input.add(row);
		user_input.add(rowInput);
		
		col = new JLabel("Column:");
		colInput = new JTextField("");
		user_input.add(col);
		user_input.add(colInput);
		
		buttons = new JPanel();

		buttons.setVisible(true);
		make_move = new JButton("Make your move");
		buttons.setLayout(new BorderLayout(10, 10));
		buttons.add(make_move, "Center");
		
		add(game, "North");
		add(user_input, "Center");
		add(buttons, "South");

	}

	//
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
