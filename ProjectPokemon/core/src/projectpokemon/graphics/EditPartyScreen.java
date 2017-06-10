package projectpokemon.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import projectpokemon.logic.Move;
import projectpokemon.logic.MoveFactory;
import projectpokemon.logic.Pokemon;

public class EditPartyScreen extends ScreenAdapter{
	
	private Stage stage;
	private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	
	private ImageButton[] pokemons = new ImageButton[6];
	
	private TextButton mainMenu;
	
	private Texture pokemonTextures[];
	
	private Sprite background;
	
	private SelectBox<String> switchPokemon;
	
	private SelectBox<String> move0;
	private SelectBox<String> move1;
	private SelectBox<String> move2;
	private SelectBox<String> move3;
	
	private Label PokemonStats[] = new Label[2];
	private ArrayList<Label[]> MoveStats = new ArrayList<Label[]>();
	
	private int currentPokemon = 0;
	
	private ArrayList<Integer> pokemonIDs = Pokemon.getAllPokemonIDs("pokemon.txt");
	
	private enum Types {Normal, Fighting, Flying, Poison, Ground, Rock, Bug, Ghost, Steel, Fire, Water, Grass, Eletric, Psychic, Ice, Dragon, Dark}
	
	public EditPartyScreen(){		
		initStage();
		initTextures();
		initButtons();
		initLabels();
		initSelectBoxes();
	}
	
	@Override
	public void render (float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		SpriteBatch batch = new SpriteBatch();

		batch.begin();
		background.draw(batch);
		batch.end();
		batch.dispose();
		
		
		stage.act(delta);
		stage.draw();	
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);	
		background.setSize(width, height);
	}

	@Override
	public void dispose() {
		for(int i = 0; i < pokemonTextures.length; i++){
			pokemonTextures[i].dispose();
		}
		stage.dispose();
		skin.dispose();
		background.getTexture().dispose();
	}

	public void initStage(){
		stage = new Stage(new StretchViewport(1920, 1080));
		Gdx.input.setInputProcessor(stage);
		Engine.getInstance().setBgm("edit_party");
		
	}
	
	public void initTextures(){
		pokemonTextures = new Texture[6];
		String filename;
		for(int i = 0; i < 6; i++){
			filename = String.valueOf(Engine.getInstance().getPlayer().getPokemonAt(i).getID()) + "_f.png";
			pokemonTextures[i] = new Texture(Gdx.files.internal("./pokemon_sprites/" + filename));
		}
		background = new Sprite(new Texture(Gdx.files.internal("./backgrounds/editParty.png")));
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			
	}
	
	
	public void initButtons(){
		
		initPokemonButton0();
		initPokemonButton1();
		initPokemonButton2();
		initPokemonButton3();
		initPokemonButton4();
		initPokemonButton5();
		
		initMainMenuButton();
	}
		
	public void initPokemonButton0(){
		
		TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion(pokemonTextures[0]));
		trd.setMinWidth(160.0f);
		trd.setMinHeight(160.0f);
		pokemons[0] = new ImageButton(trd);
		pokemons[0].setPosition(320 *0 + 81, 803);
		pokemons[0].setSize(160, 160);
		pokemons[0].addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						currentPokemon = 0;
						infoUpdate();
					}
				}
				);
		stage.addActor(pokemons[0]);
	}
	
	public void initPokemonButton1(){
		TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion(pokemonTextures[1]));
		trd.setMinWidth(160.0f);
		trd.setMinHeight(160.0f);
		pokemons[1] = new ImageButton(trd);
		pokemons[1].setPosition(320 *1 + 81, 803);
		pokemons[1].setSize(160, 160);
		pokemons[1].addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						currentPokemon = 1;
						infoUpdate();
					}
				}
				);
		stage.addActor(pokemons[1]);
	}
	
	public void initPokemonButton2(){
		TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion(pokemonTextures[2]));
		trd.setMinWidth(160.0f);
		trd.setMinHeight(160.0f);
		pokemons[2] = new ImageButton(trd);
		pokemons[2].setPosition(320 *2 + 81, 803);
		pokemons[2].setSize(160, 160);
		pokemons[2].addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						currentPokemon = 2;
						infoUpdate();
					}
				}
				);
		stage.addActor(pokemons[2]);
	}
	
	public void initPokemonButton3(){
		TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion(pokemonTextures[3]));
		trd.setMinWidth(160.0f);
		trd.setMinHeight(160.0f);
		pokemons[3] = new ImageButton(trd);
		pokemons[3].setPosition(320 *3 + 81, 803);
		pokemons[3].setSize(160, 160);
		pokemons[3].addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						currentPokemon = 3;
						infoUpdate();
					}
				}
				);
		stage.addActor(pokemons[3]);
	}
	
	public void initPokemonButton4(){
		TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion(pokemonTextures[4]));
		trd.setMinWidth(160.0f);
		trd.setMinHeight(160.0f);
		pokemons[4] = new ImageButton(trd);
		pokemons[4].setPosition(320 *4 + 81, 803);
		pokemons[4].setSize(160, 160);
		pokemons[4].addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						currentPokemon = 4;
						infoUpdate();
					}
				}
				);
		stage.addActor(pokemons[4]);
	}
	
	public void initPokemonButton5(){
		TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion(pokemonTextures[5]));
		trd.setMinWidth(160.0f);
		trd.setMinHeight(160.0f);
		pokemons[5] = new ImageButton(trd);
		pokemons[5].setPosition(320 *5 + 81, 803);
		pokemons[5].setSize(160, 160);
		pokemons[5].addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().setSfx("click");
						currentPokemon = 5;
						infoUpdate();
					}
				}
				);
		stage.addActor(pokemons[5]);
	}
	
	public void initMainMenuButton(){
		mainMenu = new TextButton("Main Menu", skin);
		mainMenu.setSize(150, 50);
		mainMenu.setPosition(1650, 150);
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
		
	
	public void initSelectBoxes(){
		initSwitchPokemon();
		initMove0();
		initMove1();
		initMove2();
		initMove3();
	}
	
	public void initSwitchPokemon(){		
		switchPokemon = new SelectBox<String>(skin);
		switchPokemon.setPosition(150, 700);
		switchPokemon.setSize(250, 75);
		switchPokemon.setScaleX(3.0f);
		
		String pokemonList[] = new String[pokemonIDs.size()];
		for(int i = 0; i < pokemonList.length ; i++){
			pokemonList[i] = new Pokemon(pokemonIDs.get(i)).getName();
		}
		
		switchPokemon.setItems(pokemonList);
		switchPokemon.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Engine.getInstance().setSfx("click");
				Engine.getInstance().getPlayer().swapPokemonWith(currentPokemon, new Pokemon( pokemonIDs.get(((SelectBox<String>)actor).getSelectedIndex())));  
				
				pokemonTextures[currentPokemon].dispose();
				
				String filename = String.valueOf(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getID()) + "_f.png";
				pokemonTextures[currentPokemon] = new Texture(Gdx.files.internal("./pokemon_sprites/" + filename));
				TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion(pokemonTextures[currentPokemon]));
				trd.setMinWidth(160.0f);
				trd.setMinHeight(160.0f);
				pokemons[currentPokemon].getStyle().imageDown = trd;
				pokemons[currentPokemon].getStyle().imageUp = trd;
				infoUpdate();
				
			}
			
		});
		
		
		
		stage.addActor(switchPokemon);
	}
	
	public void initMove0(){		
		move0 = new SelectBox<String>(skin);
		move0.setPosition(150, 500);
		move0.setSize(250, 75);
		move0.setScaleX(3.0f);
		
		String moveList[] = new String[Move.getNumberOfMoves("moves.txt")];
		for(int i = 0; i < Move.getNumberOfMoves("moves.txt"); i++){
			moveList[i] = MoveFactory.getInstance().getMove(i).getName();
		}
		
		move0.setItems(moveList);
		move0.setSelectedIndex(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().get(0).getId());
		move0.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Engine.getInstance().setSfx("click");
				Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().set(0, MoveFactory.getInstance().getMove(((SelectBox<String>) actor).getSelectedIndex()));   
				updateMoveLabel(0);
			}
			
		});
		
		
		stage.addActor(move0);
	}
	
	public void initMove1(){		
		move1 = new SelectBox<String>(skin);
		move1.setPosition(150, 380);
		move1.setSize(250, 75);
		move1.setScaleX(3.0f);
		
		String moveList[] = new String[Move.getNumberOfMoves("moves.txt")];
		for(int i = 0; i < Move.getNumberOfMoves("moves.txt"); i++){
			moveList[i] = MoveFactory.getInstance().getMove(i).getName();
		}
		
		move1.setItems(moveList);
		move1.setSelectedIndex(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().get(1).getId());
		move1.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Engine.getInstance().setSfx("click");
				Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().set(1, MoveFactory.getInstance().getMove(((SelectBox<String>) actor).getSelectedIndex()));   
				updateMoveLabel(1);
			}
			
		});
		
		
		stage.addActor(move1);
	}
	
	public void initMove2(){		
		move2 = new SelectBox<String>(skin);
		move2.setPosition(150, 260);
		move2.setSize(250, 75);
		move2.setScaleX(3.0f);
		
		String moveList[] = new String[Move.getNumberOfMoves("moves.txt")];
		for(int i = 0; i < Move.getNumberOfMoves("moves.txt"); i++){
			moveList[i] = MoveFactory.getInstance().getMove(i).getName();
		}
		
		move2.setItems(moveList);
		move2.setSelectedIndex(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().get(2).getId());
		move2.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Engine.getInstance().setSfx("click");
				Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().set(2, MoveFactory.getInstance().getMove(((SelectBox<String>) actor).getSelectedIndex()));   
				updateMoveLabel(2);
			}
			
		});
		
		
		stage.addActor(move2);
	}
	
	public void initMove3(){		
		move3 = new SelectBox<String>(skin);
		move3.setPosition(150, 140);
		move3.setSize(250, 75);
		move3.setScaleX(3.0f);
		
		String moveList[] = new String[Move.getNumberOfMoves("moves.txt")];
		for(int i = 0; i < Move.getNumberOfMoves("moves.txt"); i++){
			moveList[i] = MoveFactory.getInstance().getMove(i).getName();
		}
		
		move3.setItems(moveList);
		move3.setSelectedIndex(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().get(3).getId());
		move3.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Engine.getInstance().setSfx("click");
				Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().set(3, MoveFactory.getInstance().getMove(((SelectBox<String>) actor).getSelectedIndex()));   
				updateMoveLabel(3);
			}
			
		});
		
		
		stage.addActor(move3);
	}
	
	public void initLabels(){
		initLabelPokemon();		
		initMoveLabels();
	}
	
	public void initLabelPokemon(){
		PokemonStats[0] = new Label("", skin);
		PokemonStats[0].setPosition(450, 750);
		PokemonStats[0].setFontScale(1.5f);
		
		
		PokemonStats[1] = new Label("", skin);
		PokemonStats[1].setPosition(450, 720);
		PokemonStats[1].setFontScale(1.5f);
		
		updateLabelPokemon();
		
		stage.addActor(PokemonStats[0]);
		stage.addActor(PokemonStats[1]);
	}
	
	public void updateLabelPokemon(){
		String text = new String();
		text += "Name: " + Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getName() 
			+", Type(s): " + Types.values()[(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getType1())];
		if(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).hasTwoTypes())
			text += ", " + Types.values()[(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getType2())];
		PokemonStats[0].setText(text);
		
		text = "";
		text += "HP: " + String.valueOf(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getHP())
			+ ", ATK: " + String.valueOf(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getATK())
			+ ", DEF: " + String.valueOf(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getDEF(false))
			+ ", SPATK: " + String.valueOf(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getSPATK())
			+ ", SPDEF: " + String.valueOf(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getSPDEF(false))
			+ ", SPD: " + String.valueOf(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getSPD());
		PokemonStats[1].setText(text);
	}
	
	public void initMoveLabels(){
		for(int i = 0; i < 4; i++){
			MoveStats.add(new Label[3]);
			initMoveLabel(i);
		}
		
	}
	
	public void initMoveLabel(int index){
		MoveStats.get(index)[0] = new Label("", skin);
		MoveStats.get(index)[0].setPosition(450, 570 - 120 * index);
		MoveStats.get(index)[0].setFontScale(1.5f);
		
		
		MoveStats.get(index)[1] = new Label("", skin);
		MoveStats.get(index)[1].setPosition(450, 540 - 120 * index);
		MoveStats.get(index)[1].setFontScale(1.5f);
		
		MoveStats.get(index)[2] = new Label("", skin);
		MoveStats.get(index)[2].setPosition(450, 510 - 120 * index);
		MoveStats.get(index)[2].setFontScale(1.5f);
		
		updateMoveLabel(index);
		
		stage.addActor(MoveStats.get(index)[0]);
		stage.addActor(MoveStats.get(index)[1]);
		stage.addActor(MoveStats.get(index)[2]);
	}
	
	public void updateMoveLabel(int index){
		String text = new String();
		Move m = Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().get(index);
		text += "Type: " + Types.values()[(m.getType())]
		+ ", Power: " + m.getPower()
		+ ", Accuracy: " + m.getAccuracy();
		if(m.isSpecial())
				text += ", Special";
		else
			text += ", Physical";
		text += ", StatStages: ";
		MoveStats.get(index)[0].setText(text);
		
		text = "";
		text += "(Self) ATK: " + String.valueOf(m.getSelfStatStages().getAtkStage())
			+ ", DEF: " + String.valueOf(m.getSelfStatStages().getDefStage())
			+ ", SPATK: " + String.valueOf(m.getSelfStatStages().getSpatkStage())
			+ ", SPDEF: " + String.valueOf(m.getSelfStatStages().getSpdefStage())
			+ ", SPD: " + String.valueOf(m.getSelfStatStages().getSpdStage())
			+ ", Accuracy: " + String.valueOf(m.getSelfStatStages().getAccuracyStage())
			+ ", Evasion: " + String.valueOf(m.getSelfStatStages().getEvasionStage());
		MoveStats.get(index)[1].setText(text);
		
		text = "";
		text += "(Enemy) ATK: " + String.valueOf(m.getEnemyStatStages().getAtkStage())
		+ ", DEF: " + String.valueOf(m.getEnemyStatStages().getDefStage())
		+ ", SPATK: " + String.valueOf(m.getEnemyStatStages().getSpatkStage())
		+ ", SPDEF: " + String.valueOf(m.getEnemyStatStages().getSpdefStage())
		+ ", SPD: " + String.valueOf(m.getEnemyStatStages().getSpdStage())
		+ ", Accuracy: " + String.valueOf(m.getEnemyStatStages().getAccuracyStage())
		+ ", Evasion: " + String.valueOf(m.getEnemyStatStages().getEvasionStage());
		MoveStats.get(index)[2].setText(text);
	}
	
	public void infoUpdate(){
		updateLabelPokemon();
		move0.setSelectedIndex(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().get(0).getId());
		move1.setSelectedIndex(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().get(1).getId());
		move2.setSelectedIndex(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().get(2).getId());
		move3.setSelectedIndex(Engine.getInstance().getPlayer().getPokemonAt(currentPokemon).getMoveSet().get(3).getId());
		updateMoveLabel(0);
		updateMoveLabel(1);
		updateMoveLabel(2);
		updateMoveLabel(3);
	}

}
