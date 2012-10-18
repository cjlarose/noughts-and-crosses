package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Game;

public class TextFieldInputView extends JPanel implements Observer {
	private Game g;
//		had this initially, but making each new gui may need to be done 
//		by the main gui, now that I think about it
////	public static void main (String [] args) {
////		new TextFieldInputView().setVisible(true);
////	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public TextFieldInputView (Game g) {
		JInternalFrame game = new JInternalFrame();
		game.setLayout(new BorderLayout(10, 10));
		JTextArea game_view = new JTextArea();
		game_view.setEditable(false);
		game_view.setFocusable(false);
		
		Font monospace = new Font("Monospaced", Font.PLAIN, 16);
		
		game_view.setFont(monospace);
		game_view.setText(g.toString());
		game.add(game_view, "center");
		
		JInternalFrame row_stuff = new JInternalFrame();
		row_stuff.setLayout(new GridLayout(1, 2));
		JLabel row = new JLabel("Row:");
		JTextField rowInput = new JTextField("");
		row_stuff.add(row);
		row_stuff.add(rowInput);
		game.add(row_stuff);
		
		JInternalFrame col_stuff = new JInternalFrame();
		col_stuff.setLayout(new GridLayout(1, 2));
		JLabel col = new JLabel("Column:");
		JTextField colInput = new JTextField("");
		col_stuff.add(col);
		col_stuff.add(colInput);
		game.add(col_stuff);
		
		JInternalFrame buttons = new JInternalFrame();
		JButton makeMove = new JButton("Make your move");
		buttons.setLayout(new BorderLayout(10, 10));
		buttons.add(makeMove, "center");
		game.add(buttons);
		
		setLayout(new GridLayout(4, 1));
		setName("Naughts and Crosses");
		add(game);
	}

}
