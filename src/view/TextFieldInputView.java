package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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

	public TextFieldInputView() {

		this.c = new Controller(this);

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

		private void makeMove() {
			int i = Integer.parseInt(rowInput.getText());
			int j = Integer.parseInt(colInput.getText());
			colInput.setText("");
			rowInput.setText("");
			rowInput.requestFocus();
			c.makeMove(i, j);
		}
		
		public class MoveButtonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked");
				makeMove();
			}

		}

		public class EnterKeyListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(colInput.getText().equals("") || rowInput.getText()
						.equals(""))) {
					System.out.println("Key pressed");
					makeMove();
				}
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
