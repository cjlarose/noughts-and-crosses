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
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

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
		beginner.addMenuKeyListener(new MenuItemListener());
		intermediate = new JMenuItem("Intermediate");
		intermediate.addMenuKeyListener(new MenuItemListener());
		change_difficulty.add(beginner);
		change_difficulty.add(intermediate);
		game_menu.add(change_difficulty);
		new_game = new JMenuItem("New Game");
		new_game.addMenuKeyListener(new MenuItemListener());
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
		text_view_option.addMenuKeyListener(new MenuItemListener());
		graphical_view_option = new JMenuItem("Graphical View");
		graphical_view_option.addMenuKeyListener(new MenuItemListener());
		change_view.add(text_view_option);
		change_view.add(graphical_view_option);
		
//		Container content = this.getContentPane();
//		content.setLayout(new GridLayout(2, 2, 10, 10));
		this.add(default_view, "Center");
		this.setJMenuBar(menu_options);
		this.pack();
	}
	
	private class MenuItemListener implements MenuKeyListener {

		@Override
		public void menuKeyPressed(MenuKeyEvent arg0) {
			if (arg0.getSource().equals(beginner)) {
				//Change to beginner
			}
			
			else if (arg0.getSource().equals(intermediate)) {
				//change to intermediate
			}
			
			else if (arg0.getSource().equals(new_game)) {
				//new game (keep current view, unless it is neither
				//the text view or graphical view, and then start
				//new game
				
				if (!(default_view == text_view || default_view == graphic_view)) {
					default_view = text_view;
					default_view.updateUI();
				}
			}
			
			else if (arg0.getSource().equals(text_view_option)) {
				default_view = text_view;
				default_view.updateUI();
			}
			
			else if (arg0.getSource().equals(graphical_view_option)) {
				default_view = graphic_view;
				default_view.updateUI();
			}
			
		}

		@Override
		public void menuKeyReleased(MenuKeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void menuKeyTyped(MenuKeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void setGlobalListener(GUIListener l) {
		this.gui_listener = l;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
