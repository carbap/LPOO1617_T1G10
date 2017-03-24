package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
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
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

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
	private static JLabel lblHelp = new JLabel("Press Options to decide how you want to play.");

	//edit window components
	private static JToggleButton btnWall = new JToggleButton("");
	private static JToggleButton btnDoor = new JToggleButton("");
	private static JToggleButton btnFloor = new JToggleButton("");
	private static JToggleButton btnOgre = new JToggleButton("");
	private static JToggleButton btnHero = new JToggleButton("");
	private static JToggleButton btnKey = new JToggleButton("");
	private static JLabel lblWarning = new JLabel("There must be only one hero and key.");
	private static JLabel lblWidth = new JLabel("Width: ");
	private static JTextField width = new JTextField();
	private static JLabel lblHeight = new JLabel("Height: ");
	private static JTextField height = new JTextField();
	private static JButton btnCreate = new JButton("Create Map");
	private static JToggleButton btnFinalPos = new JToggleButton("finalpos...");
	private static JToggleButton btnReset = new JToggleButton("RESET");
	
	

	private static char editChar = ' ';

	private static GraphicArea gamegraphics = new GraphicArea(50, 50);
	private static GraphicArea editLevelGraphics = new GraphicArea(50, 50);
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
		editLevelGraphics.addMouseListener(this);
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
				lblHelp.setText("Grab the key while avoiding the guard.");

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
				editFrame.getContentPane().add(edit);
				editFrame.pack();
				editFrame.setVisible(true);

				editFrame.setBounds(100, 100, 600, 600);
				editFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				editFrame.getContentPane().setLayout(null);

				editLevelGraphics.setTable(editLevel.getTableCopy());
				editFrame.getContentPane().add(editLevelGraphics);



				edit.requestFocusInWindow();
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
		lblHelp.setBounds(40, 490, 600, 40);
		mainFrame.getContentPane().add(lblHelp);



		mainFrame.getContentPane().add(gamegraphics);







		btnWall.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/wall.png")));
		btnWall.setBounds(390, 50, 42, 42);
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnWall.setSelected(true);
				editChar = 'X';
			}
		});
		editFrame.getContentPane().add(btnWall);


		btnDoor.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/door_closed.png")));
		btnDoor.setBounds(450, 50, 42, 42);
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnDoor.setSelected(true);
				editChar = 'I';
			}
		});
		editFrame.getContentPane().add(btnDoor);


		btnFloor.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/white_floor.png")));
		btnFloor.setBounds(390, 100, 42, 42);
		btnFloor.setSelected(true);
		btnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnFloor.setSelected(true);
				editChar = ' ';
			}
		});
		editFrame.getContentPane().add(btnFloor);


		btnOgre.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/ogre.png")));
		btnOgre.setBounds(450, 100, 42, 42);
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnOgre.setSelected(true);
				editChar = 'O';
			}
		});
		editFrame.getContentPane().add(btnOgre);


		btnHero.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/temp.png")));
		btnHero.setBounds(390, 150, 42, 42);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnHero.setSelected(true);
				editChar = 'H';
			}
		});
		editFrame.getContentPane().add(btnHero);


		btnKey.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/key.png")));
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnKey.setSelected(true);
				editChar = 'k';
			}
		});
		btnKey.setBounds(450, 150, 42, 42);
		editFrame.getContentPane().add(btnKey);


		btnFinalPos.setBounds(390, 600, 42, 42);
		btnFinalPos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnFinalPos.setSelected(true);
				editChar = 'f'; //n existe char f mas para reconhecer q e para indicar pos final
			}
		});
		editFrame.getContentPane().add(btnFinalPos);


		btnReset.setBounds(450, 650, 42, 42);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnReset.setSelected(true);
			}
		});
		editFrame.getContentPane().add(btnReset);
		
		
		lblWarning.setBounds(50, 370, 250, 25);
		editFrame.getContentPane().add(lblWarning);
		
		lblWidth.setBounds(50, 420, 50, 25);
		editFrame.getContentPane().add(lblWidth);
		
		width.setBounds(100, 420, 100, 25);
		editFrame.getContentPane().add(width);
		
		lblHeight.setBounds(50, 450, 50, 25);
		editFrame.getContentPane().add(lblHeight);
		
		height.setBounds(100, 450, 100, 25);
		editFrame.getContentPane().add(height);
		
		btnCreate.setBounds(100, 480, 42, 42);
		btnCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Integer w = Integer.parseInt(width.getText());
				Integer h = Integer.parseInt(height.getText());

				editLevel = new OgreLevel(w, h);
				
				editGraphicsUpdate();
			}
		});
		editFrame.getContentPane().add(btnCreate);
		
		




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
				lblHelp.setText("You grabbed the key and the exit doors opened.");
			}

			if(game.isEndLevel() ){
				if(game.isLastLevel()){
					disableMov("Congratulations! You sucessfully escaped the dungeon!");
					game.setGameOver(true);
				}
				else{

					game.changeLevel(new OgreLevel(ogreNr));
					game.setLastLevel(true);
					lblHelp.setText("You are now armed, so you can stun enemies. Grab the key and escape the dungeon!");
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

	public static void editGraphicsUpdate(){
		editLevelGraphics.setTable(editLevel.getTableCopy());
		editLevelGraphics.validate();
		editLevelGraphics.repaint();
	}

	public static void disableMov(String msg){
		lblHelp.setText(msg);
		btnUp.setEnabled(false);
		btnLeft.setEnabled(false);
		btnRight.setEnabled(false);
		btnDown.setEnabled(false);
		canPress = false;
		btnOptions.setEnabled(true);
	}

	public static void disableAllEditButtons(){
		btnWall.setSelected(false);
		btnDoor.setSelected(false);
		btnFloor.setSelected(false);
		btnOgre.setSelected(false);
		btnHero.setSelected(false);
		btnKey.setSelected(false);
		btnFinalPos.setSelected(false);
		btnReset.setSelected(false);
	} 
	
	private static boolean hasChar(char c)
	{
		for(int i = 0; i < editLevel.getTableCopy().length;i++)
		{
			for(int j = 0; j < editLevel.getTableCopy()[i].length;j++)
			{
				if(editLevel.getTableCopy()[i][j] == c)
				{
					return true;
				}
			}
		}
		return false;
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
	public void mouseClicked(MouseEvent e)
	{ 
		int indexX = (e.getX())/32;
		int indexY = (e.getY())/32;
		//tirei condicao em que fazia return quando estava out of bounds porque o tamanho vai ser variavel
		switch(editChar)
		{
		case 'X':
			editLevel.setTableChar(indexX, indexY, 'X');
			break;
		case 'I':
			editLevel.setTableChar(indexX, indexY, 'I');
			break;
		case 'H':
			if(!hasChar('H'))
			{
				editLevel.setTableChar(indexX, indexY, 'H');
			}
			break;
		case 'O':
			editLevel.setTableChar(indexX, indexY, 'O');
			break;
		case ' ':
			editLevel.setTableChar(indexX, indexY, ' ');
			break;
		case 'k':
			if(!hasChar('k'))
			{
				editLevel.setTableChar(indexX, indexY, 'k');
			}
			break;
		}
		editGraphicsUpdate();
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

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
