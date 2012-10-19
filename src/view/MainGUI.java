package view;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Game;

import controller.Controller.GUIListener;


public class MainGUI extends JFrame implements GameView {

	private GUIListener gui_listener; 
	private JMenuBar menu_options;
	private JMenu change_view;
	private JMenu change_difficulty;
	private JMenu game_menu;
	private JMenuItem text_view_option;
	private JMenuItem graphical_view_option;
	private JMenuItem beginner;
	private JMenuItem intermediate;
	private JMenuItem new_game;
	private JPanel graphic_view;
	private JPanel text_view;
	private JPanel default_view;
	
//	public void main(String[] args) {
//		MainGUI g = new MainGUI();
//		g.setVisible(true);
//	}
	
	public MainGUI() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Naughts and Crosses");
		this.setSize(400,400);
		this.setPreferredSize(new Dimension(400, 400));
		this.setLocation(100,100);
		this.setResizable(false);
		this.setVisible(true);
		this.setLayout(new BorderLayout(10, 10));
		game_menu = new JMenu("Game");
		change_difficulty = new JMenu("Change Difficulty");
		beginner = new JMenuItem("Beginner");
		intermediate = new JMenuItem("Intermediate");
		change_difficulty.add(beginner);
		change_difficulty.add(intermediate);
		game_menu.add(change_difficulty);
		new_game = new JMenuItem("New Game");
		game_menu.add(new_game);
		
		default_view = new JPanel();
		default_view.setSize(100, 100);
		default_view.setVisible(true);
		text_view = new TextFieldInputView();
		graphic_view = new DrawingMouseView();
		menu_options = new JMenuBar();
		change_view = new JMenu("View");
		menu_options.add(game_menu);
		menu_options.add(change_view);
		text_view_option = new JMenuItem("Textual View");
		graphical_view_option = new JMenuItem("Graphical View");
		change_view.add(text_view_option);
		change_view.add(graphical_view_option);
		
//		Container content = this.getContentPane();
//		content.setLayout(new GridLayout(2, 2, 10, 10));
		this.add(default_view, "Center");
		this.setJMenuBar(menu_options);
		this.pack();
	}
	
	private class ChangeViewListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
//			if(arg0.getSource().equals()) {
//				//Change to text view
//			}
//			else if(arg0.getSource().equals()) {
//				//Change to graphic view
//			}
			
		}
		
	}
	
	public void setGlobalListener(GUIListener l) {
		this.gui_listener = l;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
