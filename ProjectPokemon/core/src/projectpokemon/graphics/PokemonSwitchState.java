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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import projectpokemon.logic.BattleAI;

public class PokemonSwitchState extends ScreenAdapter{

	private Stage stage;
	private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	private BattleAI battle;

	private TextButton switch1;
	private TextButton switch2;
	private TextButton switch3;
	private TextButton switch4;
	private TextButton switch5;

	private TextButton cancel;

	private Label pokemonLabels[];
	private Sprite background;
	private Sprite pokemonSprites[];
	private Sprite hp_pokemon[];
	private Sprite hp_pokemon_bar[];

	private boolean forceSwitch;

	public PokemonSwitchState(BattleAI b, boolean forceSwitch){
		this.forceSwitch = forceSwitch;
		battle = b;
		initStage();
		initSprites();
		initLabels();
		initButtons();


	}

	@Override
	public void render (float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		SpriteBatch batch = new SpriteBatch();

		batch.begin();
		background.draw(batch);
		for(int i = 0; i < pokemonSprites.length; i++){
			hp_pokemon[i].draw(batch);
			hp_pokemon_bar[i].draw(batch);
		}
		batch.end();
		
		stage.act(delta);
		stage.draw();
		batch.begin();
		for(int i = 0; i < pokemonSprites.length; i++){
			pokemonSprites[i].draw(batch);
		}
		batch.end();
		batch.dispose();
		
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Vector2 v= new Vector2(40, 580);
		for(int i = 0; i < pokemonSprites.length; i++){
			if(i == 0){
				stage.getViewport().project(v);
				pokemonSprites[i].setPosition(v.x, v.y);
				v.set(160, 160);
				stage.getViewport().project(v);
				pokemonSprites[i].setSize(v.x, v.y);
			}
			else{
				v= new Vector2(100 + 900, 880 - (i-1) * 200);
				stage.getViewport().project(v);
				pokemonSprites[i].setPosition(v.x, v.y);
				v.set(160, 160);
				stage.getViewport().project(v);
				pokemonSprites[i].setSize(v.x, v.y);
			}

			if(i==0)
				v.set(200, 600);
			else
				v.set(300 + ((i+6)/7) * 900, 900 - ((i+6)%7) * 200);
			stage.getViewport().project(v);
			hp_pokemon[i].setPosition(v.x, v.y);
			v.set(492, 122);
			stage.getViewport().project(v);
			hp_pokemon[i].setSize(v.x, v.y);


			if(i==0)
				v.set(400, 630);
			else
				v.set(500 + ((i+6)/7) * 900, 930 - ((i+6)%7) * 200);
			stage.getViewport().project(v);
			hp_pokemon_bar[i].setPosition(v.x, v.y);
			v.set(192*battle.getPlayer1().getPokemonAt(i).getPercentageHP(),8);
			stage.getViewport().project(v);
			hp_pokemon_bar[i].setSize(v.x, v.y);

		}
	}

	@Override
	public void dispose() {
		for(int i = 0; i < pokemonSprites.length; i++){
			pokemonSprites[i].getTexture().dispose();
		}
		stage.dispose();
		skin.dispose();

	}

	public void initStage(){
		stage = new Stage(new StretchViewport(1920, 1080));
		Gdx.input.setInputProcessor(stage);

	}

	public void initSprites(){
		background = new Sprite(new Texture(Gdx.files.internal("./backgrounds/switchPokemon.jpg")));
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		pokemonSprites = new Sprite[6];
		for(int i = 0; i < pokemonSprites.length; i++){
			String filename = String.valueOf(Engine.getInstance().getPlayer().getPokemonAt(i).getID()) + "_f.png";
			pokemonSprites[i] = new Sprite(new Texture(Gdx.files.internal("./pokemon_sprites/" + filename)));
		}

		for(int i = 0; i < pokemonSprites.length; i++){
			if(i == 0){
				Vector2 v= new Vector2(320 *i + 81, 303);
				stage.getViewport().project(v);
				pokemonSprites[i].setPosition(v.x, v.y);
				v.set(160, 160);
				stage.getViewport().project(v);
				pokemonSprites[i].setSize(v.x, v.y);
			}
			else{
				Vector2 v= new Vector2(100 + 900, 880 - (i-1) * 200);
				stage.getViewport().project(v);
				pokemonSprites[i].setPosition(v.x, v.y);
				v.set(160, 160);
				stage.getViewport().project(v);
				pokemonSprites[i].setSize(v.x, v.y);
			}
		}

		initHpSprites();
	}

	public void initHpSprites(){
		hp_pokemon = new Sprite[6];
		hp_pokemon_bar = new Sprite[6];
		for(int i = 0; i < 6; i++){
			hp_pokemon[i] = new Sprite(new Texture(Gdx.files.internal("./battle_sprites/opponent_hp_bar.png")));
			hp_pokemon_bar[i] = new Sprite(new Texture(Gdx.files.internal("./battle_sprites/hp_bar.png")));
			if(i == 0){
				hp_pokemon[i].setPosition(200 , 600);
				hp_pokemon[i].setSize(492, 122);

				hp_pokemon_bar[i].setPosition(300, 842);
				hp_pokemon_bar[i].setSize(288*battle.getPlayer2().getPartyLeader().getPercentageHP(),9);
			}
			else{
				hp_pokemon[i].setPosition(300 + ((i+6)/7) * 900, 900 - (i%6) * 200);
				hp_pokemon[i].setSize(492, 122);

				hp_pokemon_bar[i].setPosition(300, 842);
				hp_pokemon_bar[i].setSize(288*battle.getPlayer2().getPartyLeader().getPercentageHP(),9);
			}
			hp_pokemon_bar[i].setColor(Color.GREEN);
		}
	}

	public void initLabels(){
		pokemonLabels = new Label[6];
		for(int i = 0; i < pokemonLabels.length; i++){
			String name = Engine.getInstance().getPlayer().getPokemonAt(i).getName();
			pokemonLabels[i] = new Label(name, skin);
			
			pokemonLabels[i].setSize(50, 20);
			pokemonLabels[i].setFontScale(2.0f);
			if(i == 0)
				pokemonLabels[i].setPosition(320, 670);
			else
				pokemonLabels[i].setPosition(420 + ((i+6)/7) * 900, 970 - ((i+6)%7) * 200);
			stage.addActor(pokemonLabels[i]);
			
		}

	}

	public void initButtons(){
		initSwitch1();
		initSwitch2();
		initSwitch3();
		initSwitch4();
		initSwitch5();
		initCancelButton();
	}

	public void initSwitch1(){
		switch1 = new TextButton("", skin);
		switch1.setSize(160, 160);
		switch1.setPosition(100 + 900, 880 - 0 * 200);
		if(battle.getPlayer1().getPokemonAt(1).getCurrentHP() <= 0){
			switch1.setTouchable(Touchable.disabled);
			switch1.setColor(switch1.getColor().r, switch1.getColor().g, switch1.getColor().b, 0.5f);
		}
		switch1.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						battle.getVariables().setPlayer1Switch(true);
						battle.switchPokemon(1, battle.getPlayer1());
						ScreenFactory screens = ScreenFactory.getInstance();
						if(forceSwitch)
							screens.getGame().setScreen(screens.getPlayerDecideState(battle));
						else
							screens.getGame().setScreen(screens.getBattleTurnState(battle, 0));
					}
				}
				);
		stage.addActor(switch1);
	}

	public void initSwitch2(){
		switch2 = new TextButton("", skin);
		switch2.setSize(160, 160);
		switch2.setPosition(100 + 900, 880 - 1 * 200);
		if(battle.getPlayer1().getPokemonAt(2).getCurrentHP() <= 0){
			switch2.setTouchable(Touchable.disabled);
			switch2.setColor(switch2.getColor().r, switch2.getColor().g, switch2.getColor().b, 0.5f);
		}
		switch2.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						battle.getVariables().setPlayer1Switch(true);
						battle.switchPokemon(2, battle.getPlayer1());
						ScreenFactory screens = ScreenFactory.getInstance();
						if(forceSwitch)
							screens.getGame().setScreen(screens.getPlayerDecideState(battle));
						else
							screens.getGame().setScreen(screens.getBattleTurnState(battle, 0));
					}
				}
				);
		stage.addActor(switch2);
	}

	public void initSwitch3(){
		switch3 = new TextButton("", skin);
		switch3.setSize(160, 160);
		switch3.setPosition(100 + 900, 880 - 2 * 200);
		if(battle.getPlayer1().getPokemonAt(3).getCurrentHP() <= 0){
			switch3.setTouchable(Touchable.disabled);
			switch3.setColor(switch3.getColor().r, switch3.getColor().g, switch3.getColor().b, 0.5f);
		}
		switch3.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						battle.getVariables().setPlayer1Switch(true);
						battle.switchPokemon(3, battle.getPlayer1());
						ScreenFactory screens = ScreenFactory.getInstance();
						if(forceSwitch)
							screens.getGame().setScreen(screens.getPlayerDecideState(battle));
						else
							screens.getGame().setScreen(screens.getBattleTurnState(battle, 0));
					}
				}
				);
		stage.addActor(switch3);
	}

	public void initSwitch4(){
		switch4 = new TextButton("", skin);
		switch4.setSize(160, 160);
		switch4.setPosition(100 + 900, 880 - 3 * 200);
		if(battle.getPlayer1().getPokemonAt(4).getCurrentHP() <= 0){
			switch4.setTouchable(Touchable.disabled);
			switch4.setColor(switch4.getColor().r, switch4.getColor().g, switch4.getColor().b, 0.5f);
		}
		switch4.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						battle.getVariables().setPlayer1Switch(true);
						battle.switchPokemon(4, battle.getPlayer1());
						ScreenFactory screens = ScreenFactory.getInstance();
						if(forceSwitch)
							screens.getGame().setScreen(screens.getPlayerDecideState(battle));
						else
							screens.getGame().setScreen(screens.getBattleTurnState(battle, 0));
					}
				}
				);
		stage.addActor(switch4);
	}

	public void initSwitch5(){
		switch5 = new TextButton("", skin);
		switch5.setSize(160, 160);
		switch5.setPosition(100 + 900, 880 - 4 * 200);
		if(battle.getPlayer1().getPokemonAt(5).getCurrentHP() <= 0){
			switch5.setTouchable(Touchable.disabled);
			switch5.setColor(switch5.getColor().r, switch5.getColor().g, switch5.getColor().b, 0.5f);
		}
		switch5.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						battle.getVariables().setPlayer1Switch(true);
						battle.switchPokemon(5, battle.getPlayer1());
						ScreenFactory screens = ScreenFactory.getInstance();
						if(forceSwitch)
							screens.getGame().setScreen(screens.getPlayerDecideState(battle));
						else
							screens.getGame().setScreen(screens.getBattleTurnState(battle, 0));
					}
				}
				);
		stage.addActor(switch5);
	}

	public void initCancelButton(){
		cancel = new TextButton("Cancel", skin);
		cancel.getLabel().setFontScale(2.0f);
		cancel.setSize(400, 100);
		cancel.setPosition(200, 900);
		if(forceSwitch){
			cancel.setTouchable(Touchable.disabled);
			cancel.setColor(cancel.getColor().r, cancel.getColor().g, cancel.getColor().b, 0.5f);
		}
		cancel.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						ScreenFactory screens = ScreenFactory.getInstance();
						screens.getGame().setScreen(screens.getPlayerDecideState(battle));
					}
				}
				);
		stage.addActor(cancel);
	}

}

