package projectpokemon.graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Locale;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import projectpokemon.logic.MoveFactory;

public class MainMenuScreen extends ScreenAdapter{

	private Stage stage;
	private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	
	private TextButton battleAI;
	private TextButton tradeParty;
	private TextButton editParty;
	private TextButton exit;

	private Slider volumeSlider;

	private Label pokemonLabels[];
	private Label volumeLabel;
	private Sprite background;
	private Sprite pokemonSprites[];

	public MainMenuScreen(){
		initStage();
		initSprites();
		initLabels();
		initButtons();
		initSlider();
	}

	@Override
	public void render (float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		SpriteBatch batch = new SpriteBatch();

		batch.begin();
		background.draw(batch);
		for(int i = 0; i < pokemonSprites.length; i++){
			pokemonSprites[i].draw(batch);

		}
		batch.end();
		batch.dispose();
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
		background.setSize(width, height);
		for(int i = 0; i < pokemonSprites.length; i++){
			Vector2 v= new Vector2(320 *i + 81, 303);
			stage.getViewport().project(v);
			pokemonSprites[i].setPosition(v.x, v.y);
			v.set(160, 160);
			stage.getViewport().project(v);
			pokemonSprites[i].setSize(v.x, v.y);
		}
	}

	@Override
	public void dispose() {
		for(int i = 0; i < pokemonSprites.length; i++){
			pokemonSprites[i].getTexture().dispose();
		}
		stage.dispose();
		skin.dispose();
		background.getTexture().dispose();
	}

	public void initStage(){
		stage = new Stage(new StretchViewport(1920, 1080));
		Gdx.input.setInputProcessor(stage);
		Engine.getInstance().setBgm("main_menu");
		
	}

	public void initSprites(){
		background = new Sprite(new Texture(Gdx.files.internal("./backgrounds/titleScreen.jpg")));
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		pokemonSprites = new Sprite[6];
		for(int i = 0; i < pokemonSprites.length; i++){
			String filename = String.valueOf(Engine.getInstance().getPlayer().getPokemonAt(i).getID()) + "_f.png";
			pokemonSprites[i] = new Sprite(new Texture(Gdx.files.internal("./pokemon_sprites/" + filename)));
		}
		

		for(int i = 0; i < pokemonSprites.length; i++){
			Vector2 v= new Vector2(320 *i + 81, 303);
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
			String name = Engine.getInstance().getPlayer().getPokemonAt(i).getName();
			pokemonLabels[i] = new Label(name, skin);
		}

		for(int i = 0; i < pokemonLabels.length; i++){
			pokemonLabels[i].setSize(50, 20);
			pokemonLabels[i].setFontScale(2.0f);
			pokemonLabels[i].setPosition(320 *i + 111, 273);
			stage.addActor(pokemonLabels[i]);
		}

		volumeLabel = new Label("", skin);
		int volumePercent = Math.round(Engine.getInstance().getVolume()*100);
		volumeLabel.setText(String.valueOf(volumePercent)+"%");
		volumeLabel.setSize(15, 10);
		volumeLabel.setPosition(210, 55);
		stage.addActor(volumeLabel);
	}

	public void initButtons(){
		initBattleAIButton();
		initUpdateButton();
		initEditPartyButton();
		initExitButton();
	}

	public void initBattleAIButton(){
		battleAI = new TextButton("Battle AI", skin);
		battleAI.setSize(400, 200);
		battleAI.setPosition(126, 668);
		battleAI.getLabel().setFontScale(2.5f);
		
		battleAI.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						ScreenFactory screens = ScreenFactory.getInstance();
						screens.getGame().setScreen(screens.getBattleAIScreen(Engine.getInstance().getInfo().getCurrentOpponentIndex()));
					}
				}
				);
		stage.addActor(battleAI);
	}

	public void initUpdateButton(){
		tradeParty = new TextButton("Update", skin);
		tradeParty.setSize(400, 200);
		tradeParty.setPosition(1322, 668);
		tradeParty.getLabel().setFontScale(2.5f);
		
		tradeParty.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						updateGame();
					}
				}
				);
		stage.addActor(tradeParty);
	}

	public void initEditPartyButton(){
		editParty = new TextButton("Edit Party", skin);
		editParty.setSize(400, 200);
		editParty.setPosition(724, 668);
		editParty.getLabel().setFontScale(2.5f);
		
		editParty.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						ScreenFactory screens = ScreenFactory.getInstance();
						screens.getGame().setScreen(screens.getEditPartyScreen());
					}
				}
				);
		stage.addActor(editParty);
	}

	public void initExitButton(){
		exit = new TextButton("Exit", skin);
		exit.setSize(194, 73);
		exit.setPosition(1680, 84);
		exit.getLabel().setFontScale(2.5f);
		
		exit.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Gdx.app.exit();
					}
				}
				);
		stage.addActor(exit);
	}
	
	public void initSlider(){
		volumeSlider = new Slider(0.0f, 1.0f, 0.05f, false, skin);
		volumeSlider.setSize(150, 20);
		volumeSlider.setPosition(50, 50);
		volumeSlider.setValue(Engine.getInstance().getVolume());
		volumeSlider.addListener(
				new ChangeListener()
				{
					@Override
					public void changed(ChangeEvent event, Actor actor)
					{
						Engine engine = Engine.getInstance();
						engine.setSfx("click");
						engine.setVolume(((Slider) actor).getValue());
						int volumePercent = Math.round(((Slider) actor).getPercent()*100);
						volumeLabel.setText(String.valueOf(volumePercent)+"%");
					}
				}
				);
		stage.addActor(volumeSlider);
	}
	
	public void updateGame(){
		URL website;
		try {
			website = new URL("https://pastebin.com/raw/qz9whNgM");
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream("data/update.txt");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
        	File a = new File("data/update.txt");
			Scanner scanner = new Scanner(a).useLocale(Locale.US);
			String line = new String();
			String line2 = new String();
			boolean name = true;
			int counter = 0;
			while(scanner.hasNextLine()){
				if(counter < 2){
					line = scanner.nextLine();
					if(line.substring(0, 1).equals("/"))
						continue;
					URL urlRead = new URL(line);
					updateData(urlRead, counter);
					counter++;
				}
				else{
					if(name){
						line = scanner.nextLine();
						if(line.substring(0, 1).equals("/"))
							continue;
					}
					else{
						line2 = scanner.nextLine();
						URL urlRead = new URL(line2);
						updateSprites(urlRead, line);
					}
					name = !name;
				}
				
			}
			scanner.close();
		}
        catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		MoveFactory.getInstance().update();
	}
	
	
	public void updateData(URL url, int stage){
		try {
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos = new FileOutputStream("data/moves.txt");
			if(stage == 0)
				 fos = new FileOutputStream("data/pokemon.txt");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateSprites(URL url, String name){
		try {
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos = new FileOutputStream("pokemon_sprites/" + name + ".png");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
