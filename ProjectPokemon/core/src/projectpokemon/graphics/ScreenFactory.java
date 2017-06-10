package projectpokemon.graphics;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;

import projectpokemon.logic.BattleAI;

public class ScreenFactory {
	private static ScreenFactory instance = new ScreenFactory();
	private static ScreenAdapter screen = null;
	private Game g;
	
	private ScreenFactory(){}
	
	public static ScreenFactory getInstance(){
	      return instance;
	}
	
	public void setGame(Game g){
		this.g = g;
	}
	
	public Game getGame(){
		return g;
	}
	
	public ScreenAdapter getMainMenuScreen(){
		if(screen != null)
			screen.dispose();
		screen = new MainMenuScreen();
		return screen;
	}
		
	public ScreenAdapter getEditPartyScreen(){
		if(screen != null)
			screen.dispose();
		screen = new EditPartyScreen();
		return screen;
	}
	
	public ScreenAdapter getPlayerDecideState(BattleAI b){
		if(screen != null)
			screen.dispose();
		screen = new PlayerDecideState(b);
		return screen;
	}
	
	public ScreenAdapter getPokemonSwitchState(BattleAI b, boolean forceSwitch){
		if(screen != null)
			screen.dispose();
		screen = new PokemonSwitchState(b, forceSwitch);
		return screen;
	}
	
	public ScreenAdapter getBattleTurnState(BattleAI b, int turn){
		if(screen != null)
			screen.dispose();
		screen = new BattleTurnState(b, turn);
		return screen;
	}
	
	public ScreenAdapter getBattleAIScreen(int currOpponent){
		if(screen != null)
			screen.dispose();
		screen = new BattleAIScreen(currOpponent);
		return screen;
	}
	
	public ScreenAdapter getVictoryScreen(){
		if(screen != null)
			screen.dispose();
		screen = new VictoryScreen();
		return screen;
	}

}
