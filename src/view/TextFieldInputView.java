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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.Game;

import controller.Controller;

public class TextFieldInputView extends JPanel implements Observer {
	private JPanel game;
	private JTextPane game_view;
	private JPanel user_input;
	private Controller c;

	/**
	 * Creates a new text field input view with a game board and input controls.
	 */
	public TextFieldInputView(Controller c) {
		this.c = c;

		setSize(400, 340);

		// setLocation(10, 10);
		setSize(400, 300);
		setLayout(new BorderLayout(10, 10));

		// board container
		game = new JPanel();
		game.setVisible(true);
		game.setLayout(new BorderLayout(10, 10));

		// board.
		game_view = new JTextPane();
		StyledDocument doc = game_view.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		game_view.setText(c.getCurrentGame().toString());
		game_view.setEditable(false);
		game_view.setFocusable(false);

		Font monospace = new Font("Monospaced", Font.PLAIN, 70);

		game_view.setFont(monospace);
		game.add(game_view, "Center");

		this.user_input = new ControlsContainer();

		add(game, "North");
		add(user_input, "Center");

	}

	/**
	 * JPanel containing the row and column input boxes and a button to move
	 */
	public class ControlsContainer extends JPanel {

		private JTextField rowInput;
		private JTextField colInput;
		private JButton make_move;

		public ControlsContainer() {
			// controls container
			this.setVisible(true);
			this.setLayout(new FlowLayout());

			// row
			this.add(new JLabel("Row:"));
			rowInput = new JTextField();
			rowInput.setPreferredSize(new Dimension(30, 20));
			rowInput.addActionListener(new EnterKeyListener());
			this.add(rowInput);

			// column
			this.add(new JLabel("Column:"));
			colInput = new JTextField();
			colInput.setPreferredSize(new Dimension(30, 20));
			colInput.addActionListener(new EnterKeyListener());
			this.add(colInput);

			// Make move button
			make_move = new JButton("Make your move");
			make_move.addActionListener(new MoveButtonListener());
			this.add(make_move);
		}

		/**
		 * Reads the values in the input boxes and make the corresponding move on the game.
		 */
		private void makeMove() {
			int i = Integer.parseInt(rowInput.getText());
			int j = Integer.parseInt(colInput.getText());
			colInput.setText("");
			rowInput.setText("");
			rowInput.requestFocus();
			try {
				c.makeMove(i, j);
				Game curr = c.getCurrentGame();
				if (curr.isFinished()) {
					int input;
					if (curr.getWinner() == 'X') 
						input = JOptionPane.showConfirmDialog(null, "You Win! Start a new game?", "Game Over", JOptionPane.YES_NO_OPTION);
					else if (curr.getWinner() == 'O') 
						input = JOptionPane.showConfirmDialog(null, "You lose! Try again?", "Game Over", JOptionPane.YES_NO_OPTION);
					else 
						input = JOptionPane.showConfirmDialog(null, "It's a draw. Another round?", "Game Over", JOptionPane.YES_NO_OPTION);
					
					if (input == JOptionPane.YES_OPTION) 
						c.getMainGUI().newGame();
					else 
						System.exit(0);
				}
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, "You've made an illegal move", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		/**
		 * Listener for clicking the "move" button
		 */
		public class MoveButtonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				makeMove();
			}

		}

		/**
		 * Listener for pressing "enter" in a text input field
		 */
		public class EnterKeyListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(colInput.getText().equals("") || rowInput.getText()
						.equals(""))) {
					makeMove();
				}
			}

		}
	}

	/**
	 * Update the game board when the game has changed
	 */
	@Override
	public void update(Observable obj, Object arg1) {
		Game g = (Game) obj;
		if (this.game_view != null)
			this.game_view.setText(g.toString());
	}

}
