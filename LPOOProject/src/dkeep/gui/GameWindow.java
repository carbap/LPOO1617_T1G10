package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import dkeep.logic.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;

public class GameWindow extends JPanel implements KeyListener, MouseListener{

	private static JFrame mainFrame;
	private static JFrame dialogFrame;
	private static JFrame editFrame;

	private static Game game;
	private boolean keepRunning = true;
	private static boolean canPress = true;
	private static int ogreNr = 1;

	//window components
	private static JTextField textField = new JTextField();
	private static JComboBox comboBox = new JComboBox();
	private static JButton btnNewGame = new JButton("New Game");
	private static JButton btnOptions = new JButton("Options");
	private static JButton btnEdit = new JButton("Edit Keep Level");
	private static JButton btnLeft = new JButton("Left");
	private static JButton btnUp = new JButton("Up");
	private static JButton btnRight = new JButton("Right");
	private static JButton btnDown = new JButton("Down");
	private static JButton btnExit = new JButton("Exit");
	private static JLabel lblNewLabel = new JLabel("Press Options to decide how you want to play.");

	private static GraphicArea gamegraphics = new GraphicArea(50, 120);
	
	private static GraphicArea editLevelGraphics = new GraphicArea(50, 120);
	
	private static OgreLevel editLevel = new OgreLevel(ogreNr);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
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
		this.addKeyListener(this);
		this.addMouseListener(this);
		mainFrame = new JFrame();
		dialogFrame = new JFrame();
		editFrame = new JFrame();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		JPanel panel = new GameWindow();
		JPanel dialog = new JPanel();
		JPanel edit = new JPanel();

		mainFrame.getContentPane().add(panel);
		mainFrame.pack();
		mainFrame.setVisible(true);

		mainFrame.setBounds(100, 100, 800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		btnNewGame.setEnabled(false);

		//button options
		JLabel info = new JLabel();
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dialogFrame.getContentPane().add(dialog);
				dialogFrame.pack();
				dialogFrame.setVisible(true);

				dialogFrame.setBounds(100, 100, 350, 235);
				dialogFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				dialogFrame.getContentPane().setLayout(null);			

				JLabel lblTextboxOgre = new JLabel("Number of ogres");
				lblTextboxOgre.setBounds(40, 30, 120, 25);
				dialogFrame.getContentPane().add(lblTextboxOgre);

				textField.setBounds(180, 30, 100, 25);
				textField.setColumns(10);
				dialogFrame.getContentPane().add(textField);

				JLabel lblGuardPersonality = new JLabel("Guard personality");
				lblGuardPersonality.setBounds(40, 70, 120, 25);
				dialogFrame.getContentPane().add(lblGuardPersonality);

				comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
				comboBox.setMaximumRowCount(3);
				comboBox.setBounds(180, 70, 100, 25);
				dialogFrame.getContentPane().add(comboBox);

				info.setText("Please insert a number of ogres (1 to 5).");
				info.setHorizontalAlignment(SwingConstants.HORIZONTAL);
				info.setBounds(50, 110, 250, 25);
				dialogFrame.getContentPane().add(info);

				JButton btnDone = new JButton("Done");
				btnDone.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String text = textField.getText();
						Integer ogreNr;
						if(text.equals("")){
							info.setText("Please insert a number of ogres (1 to 5).");
							return;
						}
						try
						{
							ogreNr = Integer.parseInt(text);
						}
						catch (NumberFormatException exception)
						{
							info.setText("Invalid number of ogres (1 to 5).");
							return;
						} 

						if (!(ogreNr > 0 && ogreNr <= 5)) {
							info.setText("Invalid number of ogres (1 to 5).");
							return;
						}
						else
						{
							btnNewGame.setEnabled(true);
							dialogFrame.setVisible(false);
						}
						panel.requestFocusInWindow();
					}
				});
				btnDone.setBounds(125, 150, 100, 25);
				btnDone.setEnabled(true);
				dialogFrame.getContentPane().add(btnDone);
			}
		});
		btnOptions.setBounds(584, 105, 100, 25);
		mainFrame.getContentPane().add(btnOptions);

		//button new game
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canPress = true;

				ogreNr = Integer.parseInt(textField.getText());
				game = new Game(new GuardLevel(comboBox.getSelectedIndex() + 1));
				
				
				graphicsUpdate();
				
				
				btnUp.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				btnDown.setEnabled(true);
				lblNewLabel.setText("Grab the key while avoiding the guard.");

				panel.requestFocusInWindow();
			}
		});
		btnNewGame.setBounds(584, 80, 100, 25);
		mainFrame.getContentPane().add(btnNewGame);

		//button edit
				btnEdit.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						editFrame.getContentPane().add(dialog);
						editFrame.pack();
						editFrame.setVisible(true);

						editFrame.setBounds(100, 100, 600, 600);
						editFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						editFrame.getContentPane().setLayout(null);
						
						
						editFrame.getContentPane().add(editLevelGraphics);
						
						panel.requestFocusInWindow();
					}
				});
				btnEdit.setBounds(559, 300, 150, 25);
				btnEdit.setEnabled(true);
				mainFrame.getContentPane().add(btnEdit);
		
		//button left
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(game.updateHero(4));
				panel.requestFocusInWindow();
			}
		});
		btnLeft.setBounds(526, 166, 89, 23);
		btnLeft.setEnabled(false);
		mainFrame.getContentPane().add(btnLeft);

		//button up
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(game.updateHero(8));
				panel.requestFocusInWindow();
			}
		});
		btnUp.setBounds(584, 135, 89, 23);
		btnUp.setEnabled(false);
		mainFrame.getContentPane().add(btnUp);

		//button right
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(game.updateHero(6));
				panel.requestFocusInWindow();
			}
		});
		btnRight.setBounds(641, 166, 89, 23);
		btnRight.setEnabled(false);
		mainFrame.getContentPane().add(btnRight);

		//button down
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(game.updateHero(2));
				panel.requestFocusInWindow();
			}
		});
		btnDown.setBounds(584, 208, 89, 23);
		btnDown.setEnabled(false);
		mainFrame.getContentPane().add(btnDown);

		//button exit
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(615, 381, 89, 23);
		mainFrame.getContentPane().add(btnExit);

		//label game state
		lblNewLabel.setBounds(40, 490, 600, 40);
		mainFrame.getContentPane().add(lblNewLabel);

		
		
		mainFrame.getContentPane().add(gamegraphics);
		
	}

	public static void update(boolean valid){
		if(valid == true){
			if(game.isGameOver()){
				disableMov("GAMEOVER! You were caught.");
				
				graphicsUpdate();
				
				return;
			}

			game.updateGame();
			if(game.isGameOver()){
				disableMov("GAMEOVER! You were caught.");
				
				graphicsUpdate();
				
				return; 
			}
			if(!game.getLevel().isKeyEnabled()){
				lblNewLabel.setText("You grabbed the key and the exit doors opened.");
			}

			if(game.isEndLevel() ){
				if(game.isLastLevel()){
					disableMov("Congratulations! You sucessfully escaped the dungeon!");
					game.setGameOver(true);
				}
				else{
					
					game.changeLevel(new OgreLevel(ogreNr));
					game.setLastLevel(true);
					lblNewLabel.setText("You are now armed, so you can stun enemies. Grab the key and escape the dungeon!");
				}
			}
		}
		
		graphicsUpdate();
		
	}
	
	public static void graphicsUpdate()
	{
		char[][] table = game.getWorktable();
		gamegraphics.setTable(table);
		gamegraphics.validate();
		gamegraphics.repaint();
	}

	public static void disableMov(String msg){
		lblNewLabel.setText(msg);
		btnUp.setEnabled(false);
		btnLeft.setEnabled(false);
		btnRight.setEnabled(false);
		btnDown.setEnabled(false);
		canPress = false;
		btnOptions.setEnabled(true);
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		if(canPress)
		{
			int code = event.getKeyCode();
			switch(code)
			{
			case KeyEvent.VK_UP:
				update(game.updateHero(8));
				break;
			case KeyEvent.VK_LEFT:
				update(game.updateHero(4));
				break;
			case KeyEvent.VK_RIGHT:
				update(game.updateHero(6));
				break;
			case KeyEvent.VK_DOWN:
				update(game.updateHero(2));
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent event)
	{

	}

	@Override
	public void keyTyped(KeyEvent event)
	{

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub 
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
