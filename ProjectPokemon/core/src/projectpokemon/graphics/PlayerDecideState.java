package projectpokemon.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import projectpokemon.logic.BattleAI;

public class PlayerDecideState extends ScreenAdapter {

	private Stage stage;
	private BattleAI battle;

	private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

	private Sprite background;

	private Sprite base_player;
	private Sprite base_opponent;

	private Sprite hp_player;
	private Sprite hp_opponent;

	private Sprite hp_player_bar;
	private Sprite hp_opponent_bar;

	private Sprite text_box;

	private Sprite pokemon_player;
	private Sprite pokemon_opponent;

	private Label pokemon_player_name;
	private Label pokemon_opponent_name;

	private TextButton quit;
	private TextButton switchPokemon;
	private TextButton move0;
	private TextButton move1;
	private TextButton move2;
	private TextButton move3;

	public PlayerDecideState(BattleAI b){
		battle = b;
		initStage();
		initLabels();
		initSprites();		
		initButtons();

	}

	public void render (float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		SpriteBatch batch = new SpriteBatch();
		batch.begin();
		background.draw(batch);
		base_player.draw(batch);
		base_opponent.draw(batch);
		pokemon_player.draw(batch);
		pokemon_opponent.draw(batch);
		hp_player.draw(batch);
		hp_opponent.draw(batch);
		text_box.draw(batch);	
		hp_player_bar.draw(batch);
		hp_opponent_bar.draw(batch);
		batch.end();
		batch.dispose();

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
		resizeBattleStageSprites();
		resizeHpBarSprites();
		resizePokemonSprites();
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		background.getTexture().dispose();
		base_player.getTexture().dispose();
		base_opponent.getTexture().dispose();
		pokemon_player.getTexture().dispose();
		pokemon_opponent.getTexture().dispose();
		hp_player.getTexture().dispose();
		hp_opponent.getTexture().dispose();
		text_box.getTexture().dispose();
		hp_player_bar.getTexture().dispose();
		hp_opponent_bar.getTexture().dispose();
	}
	
	public void initStage(){
		stage = new Stage(new StretchViewport(1920, 1080));
		Gdx.input.setInputProcessor(stage);
	}
	
	public void initLabels(){
		pokemon_player_name = new Label(Engine.getInstance().getPlayer().getPartyLeader().getName(), skin);
		pokemon_player_name.setPosition(1400, 470);
		pokemon_player_name.setFontScale(3.0f);
		stage.addActor(pokemon_player_name);

		pokemon_opponent_name = new Label(battle.getPlayer2().getPartyLeader().getName(), skin);
		pokemon_opponent_name.setPosition(150, 890);
		pokemon_opponent_name.setFontScale(3.0f);
		stage.addActor(pokemon_opponent_name);
	}
	
	public void initSprites(){
		initBattleStageSprites();
		initHpBarSprites();
		initPokemonSprites();
	}
	
	public void initBattleStageSprites(){
		background = new Sprite(new Texture(Gdx.files.internal("./backgrounds/battle.jpg")));
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


		base_player = new Sprite(new Texture(Gdx.files.internal("./battle_sprites/base_player.png")));
		base_player.setPosition(0, 270);

		base_opponent = new Sprite(new Texture(Gdx.files.internal("./battle_sprites/base_opponent.png")));
		base_opponent.setPosition(960, 562);
		
		text_box = new Sprite(new Texture(Gdx.files.internal("./battle_sprites/text_box.png")));
		text_box.setPosition(0, 0);
		text_box.setSize(1920, 270);
	}
	
	public void initHpBarSprites(){
		hp_player = new Sprite(new Texture(Gdx.files.internal("./battle_sprites/ally_hp_bar.png")));
		hp_player.setPosition(1182, 344);
		hp_player.setSize(246, 86);

		hp_opponent = new Sprite(new Texture(Gdx.files.internal("./battle_sprites/opponent_hp_bar.png")));
		hp_opponent.setPosition(0, 806);
		hp_opponent.setSize(246, 66);

		hp_player_bar = new Sprite(new Texture(Gdx.files.internal("./battle_sprites/hp_bar.png")));
		hp_player_bar.setPosition(1584, 425);
		hp_player_bar.setSize(288* battle.getPlayer1().getPartyLeader().getPercentageHP(),9);
		hp_player_bar.setColor(Color.GREEN);

		hp_opponent_bar = new Sprite(new Texture(Gdx.files.internal("./battle_sprites/hp_bar.png")));
		hp_opponent_bar.setPosition(300, 842);
		hp_opponent_bar.setSize(288*battle.getPlayer2().getPartyLeader().getPercentageHP(),9);
		hp_opponent_bar.setColor(Color.GREEN);
	}
	
	public void initPokemonSprites(){
		String filename = String.valueOf(Engine.getInstance().getPlayer().getPartyLeader().getID()) + "_b.png";
		pokemon_player = new Sprite(new Texture(Gdx.files.internal("./pokemon_sprites/" + filename)));
		pokemon_player.setPosition(375, 270);
		pokemon_player.setSize(320, 320);


		filename = String.valueOf(battle.getPlayer2().getPartyLeader().getID()) + "_f.png";
		pokemon_opponent = new Sprite(new Texture(Gdx.files.internal("./pokemon_sprites/" + filename)));
		pokemon_opponent.setPosition(1185, 650);
		pokemon_opponent.setSize(320, 320);
	}
	
	public void resizeBattleStageSprites(){
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		Vector2 v= new Vector2(0, 270);
		stage.getViewport().project(v);
		base_player.setPosition(v.x, v.y);
		v.set(1224, 135);
		stage.getViewport().project(v);
		base_player.setSize(v.x, v.y);
		
		v.set(960, 563);
		stage.getViewport().project(v);
		base_opponent.setPosition(v.x, v.y);
		v.set(768, 288);
		stage.getViewport().project(v);
		base_opponent.setSize(v.x, v.y);
		
		v.set(1920, 270);
		stage.getViewport().project(v);
		text_box.setSize(v.x, v.y);
		
	}
	
	public void resizeHpBarSprites(){
		Vector2 v= new Vector2(1182, 344);
		stage.getViewport().project(v);
		hp_player.setPosition(v.x, v.y);
		v.set(738, 194);
		stage.getViewport().project(v);
		hp_player.setSize(v.x, v.y);
		
		v.set(0, 806);
		stage.getViewport().project(v);
		hp_opponent.setPosition(v.x, v.y);
		v.set(738, 149);
		stage.getViewport().project(v);
		hp_opponent.setSize(v.x, v.y);
		
		v.set(1584, 425);
		stage.getViewport().project(v);
		hp_player_bar.setPosition(v.x, v.y);
		v.set(288* battle.getPlayer1().getPartyLeader().getPercentageHP(),9);
		stage.getViewport().project(v);
		hp_player_bar.setSize(v.x, v.y);
		
		v.set(300, 842);
		stage.getViewport().project(v);
		hp_opponent_bar.setPosition(v.x, v.y);
		v.set(288*battle.getPlayer2().getPartyLeader().getPercentageHP(),9);
		stage.getViewport().project(v);
		hp_opponent_bar.setSize(v.x, v.y);
				
	}
	
	public void resizePokemonSprites(){
		Vector2 v= new Vector2(375, 270);
		stage.getViewport().project(v);
		pokemon_player.setPosition(v.x, v.y);
		v.set(320, 320);
		stage.getViewport().project(v);
		pokemon_player.setSize(v.x, v.y);
		
		v.set(1185, 650);
		stage.getViewport().project(v);
		pokemon_opponent.setPosition(v.x, v.y);
		v.set(320, 320);
		stage.getViewport().project(v);
		pokemon_opponent.setSize(v.x, v.y);
	}

	public void initButtons(){
		initMove0();
		initMove1();
		initMove2();
		initMove3();
		initSwitchPokemon();
		initQuit();
	}

	public void initMove0(){
		move0 = new TextButton(Engine.getInstance().getPlayer().getPartyLeader().getMoveSet().get(0).getName(), skin);
		move0.setSize(250, 125);
		move0.getLabel().setFontScale(2.0f);
		move0.setPosition(100 , 75);
		move0.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						battle.getVariables().setPlayer1MoveIndex(0);
						battle.getVariables().setPlayer1Switch(false);
						ScreenFactory screens = ScreenFactory.getInstance();
						screens.getGame().setScreen(screens.getBattleTurnState(battle, 0));
					}
				}
				);
		stage.addActor(move0);

	}

	public void initMove1(){
		move1 = new TextButton(Engine.getInstance().getPlayer().getPartyLeader().getMoveSet().get(1).getName(), skin);
		move1.setSize(250, 125);
		move1.getLabel().setFontScale(2.0f);
		move1.setPosition(100 + 275, 75);
		move1.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						battle.getVariables().setPlayer1MoveIndex(1);
						battle.getVariables().setPlayer1Switch(false);
						ScreenFactory screens = ScreenFactory.getInstance();
						screens.getGame().setScreen(screens.getBattleTurnState(battle, 0));
					}
				}
				);
		stage.addActor(move1);

	}

	public void initMove2(){
		move2 = new TextButton(Engine.getInstance().getPlayer().getPartyLeader().getMoveSet().get(2).getName(), skin);
		move2.setSize(250, 125);
		move2.getLabel().setFontScale(2.0f);
		move2.setPosition(100 + 275+ 275, 75);
		move2.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						battle.getVariables().setPlayer1MoveIndex(2);
						battle.getVariables().setPlayer1Switch(false);
						ScreenFactory screens = ScreenFactory.getInstance();
						screens.getGame().setScreen(screens.getBattleTurnState(battle, 0));
					}
				}
				);
		stage.addActor(move2);

	}

	public void initMove3(){
		move3 = new TextButton(Engine.getInstance().getPlayer().getPartyLeader().getMoveSet().get(3).getName(), skin);
		move3.setSize(250, 125);
		move3.getLabel().setFontScale(2.0f);
		move3.setPosition(100 + 275+ 275+ 275, 75);
		move3.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						battle.getVariables().setPlayer1MoveIndex(3);
						battle.getVariables().setPlayer1Switch(false);
						ScreenFactory screens = ScreenFactory.getInstance();
						screens.getGame().setScreen(screens.getBattleTurnState(battle, 0));
					}
				}
				);
		stage.addActor(move3);

	}

	public void initSwitchPokemon(){
		switchPokemon = new TextButton("Switch", skin);
		switchPokemon.setSize(250, 75);
		switchPokemon.getLabel().setFontScale(2.0f);
		switchPokemon.setPosition(100 + 275+ 275+ 275+ 275+ 275, 150);
		switchPokemon.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						ScreenFactory screens = ScreenFactory.getInstance();
						screens.getGame().setScreen(screens.getPokemonSwitchState(battle, false));
					}
				}
				);
		stage.addActor(switchPokemon);
	}
	
	public void initQuit(){
		quit = new TextButton("Quit", skin);
		quit.setSize(250, 75);
		quit.getLabel().setFontScale(2.0f);
		quit.setPosition(100 + 275+ 275+ 275+ 275+ 275, 50);
		quit.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						battle.getPlayer1().healParty();
						battle.getPlayer2().healParty();
						Engine.getInstance().getInfo().reset();
						ScreenFactory screens = ScreenFactory.getInstance();
						screens.getGame().setScreen(screens.getMainMenuScreen());
					}
				}
				);
		stage.addActor(quit);
	}
	
	
}

