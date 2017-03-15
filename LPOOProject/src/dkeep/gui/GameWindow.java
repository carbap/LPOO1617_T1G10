package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import dkeep.logic.*;
import javax.swing.DefaultComboBoxModel;

public class GameWindow {

	private JFrame frame;
	private JTextField textField;

	private Game game;
	private boolean keepRunning = true;

	//window components
	private JTextArea GameConsole = new JTextArea();
	private JComboBox comboBox = new JComboBox();
	private JButton btnNewGame = new JButton("New Game");
	private JButton btnLeft = new JButton("Left");
	private JButton btnUp = new JButton("Up");
	private JButton btnRight = new JButton("Right");
	private JButton btnDown = new JButton("Down");
	private JButton btnExit = new JButton("Exit");
	private JLabel lblNewLabel = new JLabel("Game state");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	public void update(boolean valid){
		if(valid == true){
			if(game.isGameOver()){
				disableMov("GAMEOVER!");
			}
			else{
				game.updateGame();
				if(game.isGameOver()){
					disableMov("GAMEOVER!");
				}
				else{
					if(game.isEndLevel() ){
						if(game.isLastLevel()){
							disableMov("You Win!");
							game.setGameOver(true);
						}
						else{
							Integer ogreNr = Integer.parseInt(textField.getText());
							game.changeLevel(new OgreLevel(ogreNr));
							game.setLastLevel(true);
						}
					}
				}
			}


		}
		GameConsole.setText(game.getWorktable());
	}


	public void disableMov(String msg){
		lblNewLabel.setText(msg);
		btnUp.setEnabled(false);
		btnLeft.setEnabled(false);
		btnRight.setEnabled(false);
		btnDown.setEnabled(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblTextboxOgre = new JLabel("Number of ogres");
		lblTextboxOgre.setBounds(40, 40, 120, 25);
		frame.getContentPane().add(lblTextboxOgre);

		textField = new JTextField();
		textField.setBounds(180, 40, 100, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setBounds(40, 80, 120, 25);
		frame.getContentPane().add(lblGuardPersonality);

		//combobox
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(180, 80, 100, 25);
		frame.getContentPane().add(comboBox);

		//button new game
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer ogreNr = Integer.parseInt(textField.getText());
				if(ogreNr > 0 && ogreNr <= 5){
					game = new Game(new GuardLevel(comboBox.getSelectedIndex() + 1));
					GameConsole.setText(game.getWorktable());
					btnUp.setEnabled(true);
					btnLeft.setEnabled(true);
					btnRight.setEnabled(true);
					btnDown.setEnabled(true);
					lblNewLabel.setText("Ready To Play!");
				}
				else{
					lblNewLabel.setText("Invalid number of ogres (1 to 5)");
				}
			}
		});
		btnNewGame.setBounds(584, 80, 100, 25);
		frame.getContentPane().add(btnNewGame);

		//button left
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(game.updateHero(4));

			}
		});
		btnLeft.setBounds(526, 166, 89, 23);
		btnLeft.setEnabled(false);
		frame.getContentPane().add(btnLeft);

		//button up
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(game.updateHero(8));
			}
		});
		btnUp.setBounds(584, 135, 89, 23);
		btnUp.setEnabled(false);
		frame.getContentPane().add(btnUp);

		//button right
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(game.updateHero(6));
			}
		});
		btnRight.setBounds(641, 166, 89, 23);
		btnRight.setEnabled(false);
		frame.getContentPane().add(btnRight);

		//button down
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(game.updateHero(2));
			}
		});
		btnDown.setBounds(584, 208, 89, 23);
		btnDown.setEnabled(false);
		frame.getContentPane().add(btnDown);

		//button exit
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(615, 381, 89, 23);
		frame.getContentPane().add(btnExit);

		//label game state
		lblNewLabel.setBounds(40, 490, 200, 25);
		frame.getContentPane().add(lblNewLabel);

		//textarea gameconsole
		GameConsole = new JTextArea();
		GameConsole.setFont(new Font("Courier New", Font.BOLD, 30));
		GameConsole.setBounds(50, 120, 350, 350);
		frame.getContentPane().add(GameConsole);
	}
}
