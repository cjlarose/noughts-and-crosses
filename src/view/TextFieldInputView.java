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
	private JInternalFrame game;
	private JTextArea game_view;
	private JInternalFrame row_stuff;
	private JLabel row;
	private JTextField rowInput;
	private JInternalFrame col_stuff;
	private JLabel col;
	private JTextField colInput;
	private JInternalFrame buttons;
	private JButton make_move;

	public TextFieldInputView() {
		// this.current_game = g;

		setSize(500, 800);
		setLocation(10, 10);
		setLayout(new GridLayout(4, 1));
		setName("Naughts and Crosses");

		game = new JInternalFrame();
		game.setVisible(true);
		game.setLayout(new BorderLayout(10, 10));

		game_view = new JTextArea();
		game_view.setEditable(false);
		game_view.setFocusable(false);

		Font monospace = new Font("Monospaced", Font.PLAIN, 16);

		game_view.setFont(monospace);
		// game_view.setText(current_game.toString());
		game.add(game_view, "Center");

		row_stuff = new JInternalFrame();
		row_stuff.setVisible(true);
		row_stuff.setLayout(new GridLayout(1, 2));
		row = new JLabel("Row:");
		rowInput = new JTextField("");
		row_stuff.add(row);
		row_stuff.add(rowInput);

		col_stuff = new JInternalFrame();
		col_stuff.setVisible(true);
		col_stuff.setLayout(new GridLayout(1, 2));
		col = new JLabel("Column:");
		colInput = new JTextField("");
		col_stuff.add(col);
		col_stuff.add(colInput);

		buttons = new JInternalFrame();
		buttons.setVisible(true);
		make_move = new JButton("Make your move");
		buttons.setLayout(new BorderLayout(10, 10));
		buttons.add(make_move, "Center");

		add(game);
		add(row_stuff);
		add(col_stuff);
		add(buttons);
	}

	//
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
