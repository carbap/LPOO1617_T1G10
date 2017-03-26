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
	private static JLabel lblWarning = new JLabel("Height and width must be > 5 and < 14");
	private static JLabel lblWidth = new JLabel("Width: ");
	private static JTextField width = new JTextField();
	private static JLabel lblHeight = new JLabel("Height: ");
	private static JTextField height = new JTextField();
	private static JButton btnCreate = new JButton("Create Map");
	private static JButton btnReset = new JButton("Reset Map");
	private static JButton btnSave = new JButton("Save Map");



	private static char editChar = ' ';

	private static GraphicArea gamegraphics = new GraphicArea(50, 50);
	private static GraphicArea editLevelGraphics = new GraphicArea(10, 10);
	private static OgreLevel editLevel = new OgreLevel(ogreNr);
	private static OgreLevel playableEditLevel = new OgreLevel(ogreNr);

	private static JPanel panel = new GameWindow();
	private static JPanel dialog = new JPanel();
	private static JPanel edit = new JPanel();



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
		editFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				editLevel = new OgreLevel(ogreNr);
			}
		});
	}

	public static void initializeBtnOptions(){
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
	}

	public static void initializeBtnNewGame(){
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canPress = true;

				ogreNr = Integer.parseInt(textField.getText());
				game = new Game(new GuardLevel(comboBox.getSelectedIndex() + 1));


				graphicsUpdate();

				btnEdit.setEnabled(false);
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
	}

	public static void initializeBtnEdit(){
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

				editLevelGraphics.setTable(editLevel.getWorktable());
				editFrame.getContentPane().add(editLevelGraphics);

				edit.requestFocusInWindow();
			}
		});
		btnEdit.setBounds(559, 300, 150, 25);
		btnEdit.setEnabled(true);
		mainFrame.getContentPane().add(btnEdit);
	}

	public static void initializeBtnLeft(){
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(game.updateHero(4));
				panel.requestFocusInWindow();
			}
		});
		btnLeft.setBounds(526, 166, 89, 23);
		btnLeft.setEnabled(false);
		mainFrame.getContentPane().add(btnLeft);
	}

	public static void initializeBtnUp(){
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(game.updateHero(8));
				panel.requestFocusInWindow();
			}
		});
		btnUp.setBounds(584, 135, 89, 23);
		btnUp.setEnabled(false);
		mainFrame.getContentPane().add(btnUp);
	}

	public static void initializeBtnRight(){
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(game.updateHero(6));
				panel.requestFocusInWindow();
			}
		});
		btnRight.setBounds(641, 166, 89, 23);
		btnRight.setEnabled(false);
		mainFrame.getContentPane().add(btnRight);
	}

	public static void initializeBtnDown(){
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(game.updateHero(2));
				panel.requestFocusInWindow();
			}
		});
		btnDown.setBounds(584, 208, 89, 23);
		btnDown.setEnabled(false);
		mainFrame.getContentPane().add(btnDown);
	}

	public static void initializeBtnExit(){
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(615, 381, 89, 23);
		mainFrame.getContentPane().add(btnExit);
	}



	public static void initializeButtons(){
		//button options
		initializeBtnOptions();

		//button new game
		initializeBtnNewGame();

		//button edit
		initializeBtnEdit();

		//button left
		initializeBtnLeft();

		//button up
		initializeBtnUp();

		//button right
		initializeBtnRight();

		//button down
		initializeBtnDown();

		//button exit
		initializeBtnExit();
	}


	public static void initializeBtnWall(){
		btnWall.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/wall.png")));
		btnWall.setBounds(460, 50, 42, 42);
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnWall.setSelected(true);
				editChar = 'X';
			}
		});
		editFrame.getContentPane().add(btnWall);
	}

	public static void initializeBtnDoor(){
		btnDoor.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/door_closed.png")));
		btnDoor.setBounds(520, 50, 42, 42);
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnDoor.setSelected(true);
				editChar = 'I';
			}
		});
		editFrame.getContentPane().add(btnDoor);
	}

	public static void initializeBtnFloor(){
		btnFloor.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/white_floor.png")));
		btnFloor.setBounds(460, 100, 42, 42);
		btnFloor.setSelected(true);
		btnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnFloor.setSelected(true);
				editChar = ' ';
			}
		});
		editFrame.getContentPane().add(btnFloor);
	}

	public static void initializeBtnOgre(){
		btnOgre.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/ogre.png")));
		btnOgre.setBounds(520, 100, 42, 42);
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnOgre.setSelected(true);
				editChar = 'O';
			}
		});
		editFrame.getContentPane().add(btnOgre);
	}

	public static void initializeBtnHero(){
		btnHero.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/hero_armed.png")));
		btnHero.setBounds(460, 150, 42, 42);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnHero.setSelected(true);
				editChar = 'H';
			}
		});
		editFrame.getContentPane().add(btnHero);
	}

	public static void initializeBtnKey(){
		btnKey.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/key.png")));
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableAllEditButtons();
				btnKey.setSelected(true);
				editChar = 'k';
			}
		});
		btnKey.setBounds(520, 150, 42, 42);
		editFrame.getContentPane().add(btnKey);
	}

	public static void initializeBtnReset(){
		btnReset.setBounds(350, 485, 100, 50);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editLevel = new OgreLevel(ogreNr);
				editGraphicsUpdate();
			}
		});
		editFrame.getContentPane().add(btnReset);
	}

	public static void initializeBtnCreate(){
		btnCreate.setBounds(225, 485, 100, 50);
		btnCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String stringHeight = height.getText();
				String stringWidth = width.getText();
				Integer w, h;
				if(stringHeight.equals("") || stringWidth.equals("") ){
					lblWarning.setText("Invalid dimensions. Height and width must be > 5 and < 14");
					return;
				}

				try
				{
					w = Integer.parseInt(stringWidth);
					h = Integer.parseInt(stringHeight);
				}
				catch (NumberFormatException exception)
				{
					lblWarning.setText("Invalid dimensions. Height and width must be > 5 and < 14");
					return;
				} 

				if(w > 5 && w < 14 && h > 5 && h < 14){
					editLevel = new OgreLevel(ogreNr, 3, 3, w, h);
					lblWarning.setText("Map successfully created");
					editGraphicsUpdate();
				}
				else{
					lblWarning.setText("Invalid dimensions. Height and width must be > 5 and < 14");
				}

			}
		});
		editFrame.getContentPane().add(btnCreate);
	}

	public static void initializeBtnSave(){
		btnSave.setBounds(475, 485, 100, 50);
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				boolean validMap = true;
				boolean foundDoor = false;
				int X = editLevel.getEnemies().get(0).getX();
				int Y = editLevel.getEnemies().get(0).getY();
				char[][] worktablecopy= editLevel.getWorktable();
				if((worktablecopy[X+1][Y] == 'X' || worktablecopy[X+1][Y] == 'I')
						&& (worktablecopy[X-1][Y] == 'X' || worktablecopy[X-1][Y] == 'I')
						&& (worktablecopy[X][Y+1] == 'X' || worktablecopy[X][Y+1] == 'I')
						&& (worktablecopy[X][Y-1] == 'X' || worktablecopy[X][Y-1] == 'I')){
					lblWarning.setText("Ogre must have at least one free adjacent tile");
					validMap = false;
				}
				for(int i = 0; i < worktablecopy.length && foundDoor == false; i++){
					for(int j = 0; j < worktablecopy[i].length && foundDoor == false; j++){
						if(worktablecopy[i][j] == 'I'){
							foundDoor = true;
						}
					}
				}
				if(!foundDoor){
					lblWarning.setText("There needs to be at least 1 door");
					validMap = false;
				}

				if(validMap){
					editFrame.setVisible(false);
					panel.requestFocusInWindow();
				}

			}
		});
		editFrame.getContentPane().add(btnSave);
	}




	public static void initializeEditButtons(){
		initializeBtnWall();
		initializeBtnDoor();
		initializeBtnFloor();
		initializeBtnOgre();
		initializeBtnHero();
		initializeBtnKey();
		initializeBtnReset();

		initializeBtnCreate();
		initializeBtnSave();
	}


	public static void initializeMainFrame(){
		mainFrame.getContentPane().add(panel);
		mainFrame.pack();
		mainFrame.setVisible(true);

		mainFrame.setBounds(100, 100, 800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		btnNewGame.setEnabled(false);
	}




	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		initializeMainFrame();

		initializeButtons();

		//label game state
		lblHelp.setBounds(40, 490, 600, 40);
		mainFrame.getContentPane().add(lblHelp);



		mainFrame.getContentPane().add(gamegraphics);


		initializeEditButtons();





















		lblWarning.setBounds(50, 450, 400, 25);
		editFrame.getContentPane().add(lblWarning);

		lblWidth.setBounds(50, 480, 50, 25);
		editFrame.getContentPane().add(lblWidth);

		width.setBounds(100, 480, 100, 25);
		editFrame.getContentPane().add(width);

		lblHeight.setBounds(50, 520, 50, 25);
		editFrame.getContentPane().add(lblHeight);

		height.setBounds(100, 520, 100, 25);
		editFrame.getContentPane().add(height);










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
					playableEditLevel = editLevel.getOgreLevelCopy(ogreNr);
					game.changeLevel(playableEditLevel);
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
		editLevelGraphics.setTable(editLevel.getWorktable());
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
		btnEdit.setEnabled(true);
	}

	public static void disableAllEditButtons(){
		btnWall.setSelected(false);
		btnDoor.setSelected(false);
		btnFloor.setSelected(false);
		btnOgre.setSelected(false);
		btnHero.setSelected(false);
		btnKey.setSelected(false);
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


	public void editTile(char editChar){

	}


	public void addTileX(char tableCharCopy, int indexX, int indexY){
		if(tableCharCopy != 'H' && tableCharCopy != 'O' && tableCharCopy != 'k' && tableCharCopy != 'A')
			editLevel.setTableChar(indexX, indexY, 'X');
	}

	public void addTileI(char tableCharCopy, int indexX, int indexY){
		if(tableCharCopy != 'H' && tableCharCopy != 'O' && tableCharCopy != 'k' && tableCharCopy != 'A')
			editLevel.setTableChar(indexX, indexY, 'I');
	}

	public void addTileH(char tableCharCopy, int indexX, int indexY, int tableWidth, int tableHeight){
		if(editChar == 'H' && indexX != 0 && indexX != tableWidth && indexY != 0 && indexY != tableHeight && tableCharCopy != 'X' && tableCharCopy != 'I'){
			editLevel.getHero().setPosition(indexX, indexY);
		}
	}

	public void addTileO(char tableCharCopy, int indexX, int indexY, int tableWidth, int tableHeight){
		if(indexX != 0 && indexX != tableWidth && indexY != 0 && indexY != tableHeight && tableCharCopy != 'X' && tableCharCopy != 'I'){
			editLevel.getEnemies().get(0).setPosition(indexX, indexY);
			editLevel.getEnemies().get(0).setWeaponX(indexX);
			editLevel.getEnemies().get(0).setWeaponY(indexY);
		}
	}

	public void addTileSpace(char tableCharCopy, int indexX, int indexY, int tableWidth, int tableHeight){
		if(editChar == ' ' && indexX != 0 && indexX != tableWidth && indexY != 0 && indexY != tableHeight){
			editLevel.setTableChar(indexX, indexY, ' ');
		}
	}

	public void addTileKey(char tableCharCopy, int indexX, int indexY, int tableWidth, int tableHeight){
		if(indexX != 0 && indexX != tableWidth && indexY != 0 && indexY != tableHeight && tableCharCopy != 'X' && tableCharCopy != 'I'){
			editLevel.setKeyX(indexX);
			editLevel.setKeyY(indexY);
		}
	}


	@Override 
	public void mouseClicked(MouseEvent e)
	{ 
		int indexX = (e.getX())/32;
		int indexY = (e.getY())/32;

		if(indexX >= editLevel.getTableCopy().length || indexY >= editLevel.getTableCopy()[0].length){
			return;
		}

		char tableCharCopy = editLevel.getWorktable()[indexX][indexY];
		int tableWidth =  editLevel.getWorktable().length-1;
		int tableHeight =  editLevel.getWorktable()[0].length-1;

		if(editChar == 'X')
			addTileX(tableCharCopy, indexX, indexY);
		else if(editChar == 'I')
			addTileI(tableCharCopy, indexX, indexY);
		else if(editChar == 'H')
			addTileH(tableCharCopy, indexX, indexY, tableWidth, tableHeight);
		else if(editChar == 'O')
			addTileO(tableCharCopy, indexX, indexY, tableWidth, tableHeight);
		else if(editChar == ' ')
			addTileSpace(tableCharCopy, indexX, indexY, tableWidth, tableHeight);
		else if(editChar == 'k')
			addTileKey(tableCharCopy, indexX, indexY, tableWidth, tableHeight);


		editGraphicsUpdate();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
