package dkeep.gui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GraphicArea extends JPanel{

	private int x, y;
	private char[][] table;
	private TileFactory tiles = new TileFactory();
	
			
	public GraphicArea(int x, int y){
		this.x = x;
		this.y = y;
		this.setBounds(x, y, 320, 320);
		table = new char[10][10];
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				table[i][j] = ' ';
			}
		}
	}
		
	public void paintComponent(Graphics g){
		super.paintComponent(g);
			
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table[i].length; j++){
				ImageIcon a = tiles.getImageIcon(table[i][j]);
				g.drawImage(a.getImage(), i*32, j*32, 32, 32, this);
			}
		}
		
	}
	
	public void setTable(char[][] table){
		this.table = table;
	}
	
}


