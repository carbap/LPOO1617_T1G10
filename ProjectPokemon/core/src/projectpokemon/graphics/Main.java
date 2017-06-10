package projectpokemon.graphics;

import com.badlogic.gdx.Game;

public class Main extends Game {
	
	@Override
	public void create () {
		ScreenFactory screens = ScreenFactory.getInstance();
		screens.setGame(this);
		setScreen(screens.getMainMenuScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
}
