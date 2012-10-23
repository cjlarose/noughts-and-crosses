package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import view.*;
import model.*;

/**
 * This class is responsible for the coordination between views and the model.
 * In other words, the controller is responsible for the business logic of the
 * view. For instance, the view is responsible for reporting the coordinates
 * of the cell a user chooses, but the Controller is responsible for passing
 * that move onto the Game. The view, in essence, should never directly send 
 * messages to the Game.
 */
public class Controller {

	private Game game;
	private MainGUI g;
	
	/**
	 * Running this class uses the default MainGUI
	 */
	public static void main(String[] args) {
		Controller c = new Controller();
		c.layoutGUI();
	}
	
	public Controller() {
		game = new Game();
	}
	
	public Controller(Observer toAdd) {
		game = new Game();
		game.addObserver(toAdd);
		game.notifyObservers();
	}
	
	public void newGame(Player one, Player two) {
		game = new Game();
	}
	
	public Game getCurrentGame() {
		return game;
	}
	
	private void layoutGUI() {
		g = new MainGUI();
		game.addObserver(g);
		game.notifyObservers();
	}
	
	public class MainGUI extends JFrame implements Observer {
		 
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
			beginner.addActionListener(new MenuItemListener());
			
			intermediate = new JMenuItem("Intermediate");
			intermediate.addActionListener(new MenuItemListener());
			
			new_game = new JMenuItem("New Game");
			new_game.addActionListener(new MenuItemListener());
			
			change_difficulty.add(beginner);
			change_difficulty.add(intermediate);
			
			game_menu.add(change_difficulty);
			game_menu.add(new_game);
			
			default_view = new JPanel();
			default_view.setSize(100, 100);
			default_view.setVisible(true);
			
			text_view = new TextFieldInputView();
			this.add(text_view, BorderLayout.CENTER);
			text_view.setVisible(true);
			
			graphic_view = new GraphicViewGame(null);
			this.add(graphic_view, BorderLayout.CENTER);
			graphic_view.setVisible(false);
			
			menu_options = new JMenuBar();
			change_view = new JMenu("View");
			menu_options.add(game_menu);
			menu_options.add(change_view);
			
			text_view_option = new JMenuItem("Textual View");
			text_view_option.addActionListener(new MenuItemListener());
			
			graphical_view_option = new JMenuItem("Graphical View");
			graphical_view_option.addActionListener(new MenuItemListener());
			
			change_view.add(text_view_option);
			change_view.add(graphical_view_option);
			
			//this.add(default_view, "Center");
			this.setJMenuBar(menu_options);
			this.pack();
		}
		
		private class MenuItemListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource().equals(beginner)) {
					System.out.println("Beginner mode");
					//Change to beginner
				}
				
				else if (arg0.getSource().equals(intermediate)) {
					System.out.println("Intermediate mode");
					//change to intermediate
				}
				
				else if (arg0.getSource().equals(new_game)) {
					System.out.println("New game");
					//new game (keep current view, unless it is neither
					//the text view or graphical view, and then start
					//new game
					
					if (!(default_view == text_view || default_view == graphic_view)) {
						default_view = text_view;
						default_view.updateUI();
					}
				}
				
				else if (arg0.getSource().equals(text_view_option)) {
					System.out.println("Text view");
					
					//Change to textual view
					graphic_view.setVisible(false);
					text_view.setVisible(true);
					//setSize(500,800);
					
					default_view = text_view;
					default_view.updateUI();
				}
				
				else if (arg0.getSource().equals(graphical_view_option)) {
					System.out.println("Graphical view");
					
					//Change to graphical view
					text_view.setVisible(false);
					graphic_view.setVisible(true);
					setSize(301,345);
					
					default_view = graphic_view;
					default_view.updateUI();
				}
				
			}
			
		}
		
		public void update(Observable o, Object arg) {
			
		}
	}
}
