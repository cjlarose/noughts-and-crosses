package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.AIPlayer;
import model.BeginnerStrategy;
import model.Game;
import model.HumanPlayer;
import model.Player;

//import model.Game;

import controller.Controller;
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
	private Game g;
	private Controller c;
	
	public static void main(String[] args) {
		MainGUI g = new MainGUI();
		g.setVisible(true);
	}
	
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
		
		graphic_view = new GraphicViewGame();
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
		
		default_view = text_view;
		
		this.add(default_view, "Center");
		this.setJMenuBar(menu_options);
		this.pack();
	}
	
	public void packFrame() {
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
				
//				if (!(default_view == text_view || default_view == graphic_view)) {
//					default_view = text_view;
//					default_view.updateUI();
//					packFrame();
//				}
				Player player1 = new HumanPlayer();
				Player player2 = new AIPlayer(new BeginnerStrategy());
				gui_listener.playersChosen(player1, player2);
			}
			
			else if (arg0.getSource().equals(text_view_option)) {
				System.out.println("Text view");
				
				//Change to textual view
				graphic_view.setVisible(false);
				text_view.setVisible(true);
				setSize(400,400);
				
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
	
	public void setGlobalListener(GUIListener l) {
		this.gui_listener = l;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public void setGame(Game g) {
		this.g = g;
		((TextFieldInputView) text_view).setGame(g);
		((GraphicViewGame) graphic_view).setGame(g);
	}

	public void setController(Controller c) {
		this.c = c;
		((TextFieldInputView) text_view).setController(c);
		((GraphicViewGame) graphic_view).setController(c);
	}
}
