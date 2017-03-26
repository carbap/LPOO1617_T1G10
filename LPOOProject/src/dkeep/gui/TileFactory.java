package dkeep.gui;

import java.util.HashMap;

import javax.swing.ImageIcon;

public class TileFactory {

	private HashMap<Character, ImageIcon> tiles = new HashMap<Character, ImageIcon>();
	
	TileFactory(){
		tiles.put('X', new ImageIcon(getClass().getResource("/assets/wall.png")));
		tiles.put('I', new ImageIcon(getClass().getResource("/assets/door_closed.png")));
		tiles.put('S', new ImageIcon(getClass().getResource("/assets/door_open.png")));
		tiles.put('H', new ImageIcon(getClass().getResource("/assets/hero.png")));
		tiles.put('K', new ImageIcon(getClass().getResource("/assets/hero_key.png")));
		tiles.put('A', new ImageIcon(getClass().getResource("/assets/hero_armed.png")));
		tiles.put('G', new ImageIcon(getClass().getResource("/assets/guard.png")));
		tiles.put('g', new ImageIcon(getClass().getResource("/assets/guard_asleep.png")));
		tiles.put('O', new ImageIcon(getClass().getResource("/assets/ogre.png")));
		tiles.put('8', new ImageIcon(getClass().getResource("/assets/ogre_stunned.png")));
		tiles.put('*', new ImageIcon(getClass().getResource("/assets/club.png")));
		tiles.put('k', new ImageIcon(getClass().getResource("/assets/key.png")));
		tiles.put(' ', new ImageIcon(getClass().getResource("/assets/white_floor.png")));
		tiles.put('$', new ImageIcon(getClass().getResource("/assets/unknown.png")));		
	}
	
	public ImageIcon getImageIcon(char c){
		return tiles.get(c);
	}
	
}
