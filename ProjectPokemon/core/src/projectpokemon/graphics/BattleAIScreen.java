package projectpokemon.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import projectpokemon.logic.BattleAI;
import projectpokemon.logic.InfoBattleAI;

public class BattleAIScreen extends ScreenAdapter{
	
	private Stage stage;
	
	private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	
	private TextButton battle;
	private TextButton mainMenu;
	
	private Sprite background;
	private Sprite opponentSprite;
	private Sprite pokemonSprites[];
	private Label pokemonLabels[];
	
	private int opponentIndex;
	
	
	public BattleAIScreen(int index){
		opponentIndex = index;
		initStage();
		initButtons();
		initSprites();
		initLabels();
		
	}
	
	public void render (float delta) {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		SpriteBatch batch = new SpriteBatch();

		batch.begin();
		background.draw(batch);
		opponentSprite.draw(batch);
		for(int i = 0; i < pokemonSprites.length; i++){
			pokemonSprites[i].draw(batch);

		}
		batch.end();
		batch.dispose();
		
		stage.act(delta);
		stage.draw();
	}
	
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
		background.setSize(width, height);
		Vector2 v = new Vector2();
		for(int i = 0; i < pokemonSprites.length; i++){
			v.set(320 *(i%3) + 80, 250 * (i/3) + 450);
			stage.getViewport().project(v);
			pokemonSprites[i].setPosition(v.x, v.y);
			v.set(160, 160);
			stage.getViewport().project(v);
			pokemonSprites[i].setSize(v.x, v.y);
		}
		v.set(1300, 400);
		stage.getViewport().project(v);
		opponentSprite.setPosition(v.x, v.y);
		v.set(360, 600);
		stage.getViewport().project(v);
		opponentSprite.setSize(v.x, v.y);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		background.getTexture().dispose();
		opponentSprite.getTexture().dispose();
		for(int i = 0; i < pokemonSprites.length; i++){
			pokemonSprites[i].getTexture().dispose();
		}
	}

	public void initStage(){
		stage = new Stage(new StretchViewport(1920, 1080));
		Gdx.input.setInputProcessor(stage);
		Engine.getInstance().setBgm("battle_ai_menu");
	}
	
	public void initButtons(){
		initBattleButton();
		initMainMenuButton();
	}
	
	public void initBattleButton(){
		battle = new TextButton("Battle", skin);
		battle.getLabel().setFontScale(3.0f);
		battle.setSize(600, 150);
		battle.setPosition(300, 200);
		battle.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						BattleAI b = new BattleAI(Engine.getInstance().getPlayer(), Engine.getInstance().getInfo().getCurrentOpponent());
						Engine.getInstance().setBgm("battle_" + String.valueOf(opponentIndex));
						ScreenFactory screens = ScreenFactory.getInstance();
						screens.getGame().setScreen(new PlayerDecideState(b));
					}
			    }
			);
		stage.addActor(battle);
	}
	
	public void initMainMenuButton(){
		mainMenu = new TextButton("Main Menu", skin);
		mainMenu.getLabel().setFontScale(3.0f);
		mainMenu.setSize(600, 150);
		mainMenu.setPosition(1000, 200);
		mainMenu.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						ScreenFactory screens = ScreenFactory.getInstance();
						screens.getGame().setScreen(screens.getMainMenuScreen());
					}
			    }
			);
		stage.addActor(mainMenu);
	}
	
	public void initSprites(){
		background = new Sprite(new Texture(Gdx.files.internal("./backgrounds/battleAI.jpg")));
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		InfoBattleAI a = Engine.getInstance().getInfo();
		String filename = "opponent_" + String.valueOf(a.getCurrentOpponentIndex()) + ".png";
		opponentSprite = new Sprite(new Texture(Gdx.files.internal("./opponent_sprites/" + filename)));
		opponentSprite.setPosition(1300, 400);
		opponentSprite.setSize(360, 600);
		
		pokemonSprites = new Sprite[6];
		for(int i = 0; i < pokemonSprites.length; i++){
			filename = String.valueOf(a.getCurrentOpponent().getPokemonAt(i).getID()) + "_f.png";
			pokemonSprites[i] = new Sprite(new Texture(Gdx.files.internal("./pokemon_sprites/" + filename)));
			Vector2 v= new Vector2(320 *(i%3) + 80, 250 * (i/3) + 450);
			stage.getViewport().project(v);
			pokemonSprites[i].setPosition(v.x, v.y);
			v.set(160, 160);
			stage.getViewport().project(v);
			pokemonSprites[i].setSize(v.x, v.y);
		}
	}
	
	public void initLabels(){
		pokemonLabels = new Label[6];
		for(int i = 0; i < pokemonLabels.length; i++){
			String name = Engine.getInstance().getInfo().getCurrentOpponent().getPokemonAt(i).getName();
			pokemonLabels[i] = new Label(name, skin);
		}

		for(int i = 0; i < pokemonLabels.length; i++){
			pokemonLabels[i].setSize(50, 20);
			pokemonLabels[i].setFontScale(2.0f);
			pokemonLabels[i].setPosition(320 *(i%3) + 110, 420 + (i/3) * 250);
			stage.addActor(pokemonLabels[i]);
		}
	}
	
}
