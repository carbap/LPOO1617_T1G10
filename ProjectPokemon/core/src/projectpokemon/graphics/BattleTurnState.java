package projectpokemon.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import projectpokemon.logic.BattleAI;

public class BattleTurnState extends ScreenAdapter {

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

	private Label battleText;
	private Label battleStatsText;

	private long time;
	private int currTurn;
	boolean battleEnded = false;

	public BattleTurnState(BattleAI b, int turn){
		battle = b;
		currTurn = turn;
		initStage();
		initLabels();
		
		time = System.currentTimeMillis();

		if(turn == 0){
			battle.whoAttacksFirst();
			battle.getVariables().setPlayer2Switch(battle.switchAI());


			if(battle.getVariables().isPlayer1Switch() && battle.getVariables().isPlayer2Switch()){
				if(battle.getVariables().getFirstToAttack() == 1){
					setBattleTextswitch(1);
				}	
				else{
					int choice = battle.chooseSwitch();
					if(choice != -1){
						battle.switchPokemon(choice, battle.getPlayer2());
						setBattleTextswitch(2);
					}
					else{
						battleEnded = true;
					}
				}
			}
			else if(battle.getVariables().isPlayer1Switch()){
				setBattleTextswitch(1);
			}
			else if(battle.getVariables().isPlayer2Switch()){
				int choice = battle.chooseSwitch();
				if(choice != -1){
					battle.switchPokemon(choice, battle.getPlayer2());
					setBattleTextswitch(2);
				}
				else{
					battleEnded = true;
				}

			}
			else{
				if(battle.getVariables().getFirstToAttack() == 1){
					if(battle.getPlayer1().getPartyLeader().getCurrentHP() <= 0){

					}	
					else{
						battle.attackPokemon(battle.getPlayer1().getPartyLeader(), battle.getPlayer2().getPartyLeader(), battle.getVariables().getPlayer1MoveIndex(), battle.getPlayer1());
						setBattleTextP1Attack();
						setBattleStatsText(battle.getVariables().isPlayer1Hits());
					}

				}
				else{
					if(battle.getPlayer2().getPartyLeader().getCurrentHP() <= 0){
						int choice = battle.chooseSwitch();
						if(choice != -1){
							battle.switchPokemon(choice, battle.getPlayer2());
							battle.getVariables().setPlayer2Switch(true);
							setBattleTextswitch(2);
						}
						else{
							battleEnded = true;
						}

					}	
					else{
						battle.attackPokemon(battle.getPlayer2().getPartyLeader(), battle.getPlayer1().getPartyLeader(), battle.chooseMove(), battle.getPlayer2());
						setBattleTextP2Attack();
						setBattleStatsText(battle.getVariables().isPlayer2Hits());
					}
				}
			}

		}
		else {

			if(battle.getVariables().isPlayer1Switch() && battle.getVariables().isPlayer2Switch()){
				if(battle.getVariables().getFirstToAttack() == 2){
					setBattleTextswitch(1);
				}
				else{
					int choice = battle.chooseSwitch();
					if(choice != -1){
						battle.switchPokemon(choice, battle.getPlayer2());
						setBattleTextswitch(2);
					}
					else{
						battleEnded = true;
					}
				}

			}
			else if(battle.getVariables().isPlayer1Switch()){
				if(battle.getPlayer2().getPartyLeader().getCurrentHP() <= 0){
					int choice = battle.chooseSwitch();
					if(choice != -1){
						battle.switchPokemon(choice, battle.getPlayer2());
						setBattleTextswitch(2);
					}
					else{
						battleEnded = true;
					}
				}	
				else{
					battle.attackPokemon(battle.getPlayer2().getPartyLeader(), battle.getPlayer1().getPartyLeader(), battle.chooseMove(), battle.getPlayer2());
					setBattleTextP2Attack();
					setBattleStatsText(battle.getVariables().isPlayer2Hits());
				}
			}
			else if(battle.getVariables().isPlayer2Switch()){
				battle.attackPokemon(battle.getPlayer1().getPartyLeader(), battle.getPlayer2().getPartyLeader(), battle.getVariables().getPlayer1MoveIndex(), battle.getPlayer1());
				setBattleTextP1Attack();
				setBattleStatsText(battle.getVariables().isPlayer1Hits());
			}
			else{
				if(battle.getVariables().getFirstToAttack() == 2){
					if(battle.getPlayer1().getPartyLeader().getCurrentHP() <= 0){

					}	
					else{
						battle.attackPokemon(battle.getPlayer1().getPartyLeader(), battle.getPlayer2().getPartyLeader(), battle.getVariables().getPlayer1MoveIndex(), battle.getPlayer1());
						setBattleTextP1Attack();
						setBattleStatsText(battle.getVariables().isPlayer1Hits());
					}

				}
				else{
					if(battle.getPlayer2().getPartyLeader().getCurrentHP() <= 0){
						int choice = battle.chooseSwitch();
						if(choice != -1){
							battle.switchPokemon(choice, battle.getPlayer2());
							setBattleTextswitch(2);
						}
						else{
							battleEnded = true;

						}
					}	
					else{
						battle.attackPokemon(battle.getPlayer2().getPartyLeader(), battle.getPlayer1().getPartyLeader(), battle.chooseMove(), battle.getPlayer2());
						setBattleTextP2Attack();
						setBattleStatsText(battle.getVariables().isPlayer2Hits());
					}
				}
			}

		}

		
		initSprites();
		
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

		if(System.currentTimeMillis() - time > 2000){
			ScreenFactory screens = ScreenFactory.getInstance();
			if(this.battle.getPlayer2().getPartyLeader().getCurrentHP() <= 0 && this.battle.getPlayer2().allFainted())
				this.battleEnded = true;
			if(this.battleEnded == true){
				Engine.getInstance().getPlayer().healParty();
				Engine.getInstance().getInfo().nextOpponent();
				int currIndex= Engine.getInstance().getInfo().getCurrentOpponentIndex();
				if(currIndex < 4){
					screens.getGame().setScreen(screens.getBattleAIScreen(currIndex));
				}
				else
					screens.getGame().setScreen(screens.getVictoryScreen());
			}
			else{

				if(currTurn == 0)
					if(battle.getPlayer1().getPartyLeader().getCurrentHP() <= 0){
						if(battle.getPlayer1().allFainted()){
							Engine.getInstance().getPlayer().healParty();
							Engine.getInstance().getInfo().reset();
							screens.getGame().setScreen(screens.getMainMenuScreen());
						}
							
						else{
							screens.getGame().setScreen(screens.getPokemonSwitchState(battle, true));
						}
					}		
					else
						screens.getGame().setScreen(screens.getBattleTurnState(battle, 1));
				else {
					if(battle.getPlayer2().getPartyLeader().getCurrentHP() <= 0){
						battle.getPlayer2().swapPokemonAt(0, battle.chooseSwitch());
						screens.getGame().setScreen(screens.getPlayerDecideState(battle));
					}	

					if(battle.getPlayer1().getPartyLeader().getCurrentHP() <= 0){
						if(battle.getPlayer1().allFainted()){
							Engine.getInstance().getPlayer().healParty();
							Engine.getInstance().getInfo().reset();
							screens.getGame().setScreen(screens.getMainMenuScreen());
						}
							
						else{
							screens.getGame().setScreen(screens.getPokemonSwitchState(battle, true));
						}
					}		
					else
						screens.getGame().setScreen(screens.getPlayerDecideState(battle));
				}
			}

		}

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
		
		battleText = new Label("", skin);
		battleText.setPosition(100, 180);
		battleText.setFontScale(3.0f);
		stage.addActor(battleText);
		
		battleStatsText = new Label("", skin);
		battleStatsText.setPosition(100, 100);
		battleStatsText.setFontScale(3.0f);
		stage.addActor(battleStatsText);
		
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

	
	public void setBattleTextP1Attack(){
		String text = new String();
		text += "Player's "+ battle.getPlayer1().getPartyLeader().getName() + " used " + battle.getPlayer1().getPartyLeader().getMoveSet().get(battle.getVariables().getPlayer1MoveIndex()).getName() + ". ";

		if(battle.getVariables().isPlayer1Hits()){
			int effectiveness = battle.getVariables().getPlayer1Effectiveness();
			if(effectiveness == 0)
				text += "It doesn't affect the foe. ";
			else if(effectiveness == 1)
				text += "It's not very effective. ";
			else if(effectiveness == 3)
				text += "It's super effective. ";

			if(battle.getVariables().isPlayer1Critical()){
				text += "It's a critical hit! ";
			}

		}
		else
			text += "It missed. ";

		battleText.setText(text);
	}

	public void setBattleTextP2Attack(){
		String text = new String();
		text += "Foe's "+ battle.getPlayer2().getPartyLeader().getName() + " used " + battle.getPlayer2().getPartyLeader().getMoveSet().get(battle.getVariables().getPlayer2MoveIndex()).getName() + ". ";

		if(battle.getVariables().isPlayer2Hits()){
			int effectiveness = battle.getVariables().getPlayer2Effectiveness();
			if(effectiveness == 0)
				text += "It doesn't affect the foe. ";
			else if(effectiveness == 1)
				text += "It's not very effective. ";
			else if(effectiveness == 3)
				text += "It's super effective. ";

			if(battle.getVariables().isPlayer2Critical()){
				text += "It's a critical hit! ";
			}

		}
		else
			text += "It missed. ";

		battleText.setText(text);
	}

	public void setBattleTextswitch(int player){
		String text = new String();
		if(player == 1)
			text += "Player switched to " + battle.getPlayer1().getPartyLeader().getName() + ". ";
		else{
			text += "Foe switched to " +  battle.getPlayer2().getPartyLeader().getName() + ". ";
			pokemon_opponent_name.setText(battle.getPlayer2().getPartyLeader().getName());
		}
		battleText.setText(text);
	}

	public void setBattleStatsText(boolean hits){
		String text = new String();
		text = "";
		text += checkAtkStat(hits);
		text += checkDefStat(hits);
		text += checkSpAtkStat(hits);
		text += checkSpDefStat(hits);
		text += checkSpdStat(hits);
		text += checkAccStat(hits);
		text += checkEvsStat(hits);
		battleStatsText.setText(text);
	}

	public String checkAtkStat(boolean hits){
		if(battle.getVariables().getPlayer1Stages().getAtkStage() > 0 && hits){
			return "Player's Attack rose. ";
		}
		else if(battle.getVariables().getPlayer1Stages().getAtkStage() < 0 && hits){
			return  "Player's Attack fell. ";
		}

		if(battle.getVariables().getPlayer2Stages().getAtkStage() > 0 && hits){
			return "Foe's Attack rose. ";
		}
		else if(battle.getVariables().getPlayer2Stages().getAtkStage() < 0 && hits){
			return "Foe's Attack fell. ";
		}
		return "";
	}

	public String checkDefStat(boolean hits){
		if(battle.getVariables().getPlayer1Stages().getDefStage() > 0 && hits){
			return "Player's Defense rose. ";
		}
		else if(battle.getVariables().getPlayer1Stages().getDefStage() < 0 && hits){
			return "Player's Defense fell. ";
		}

		if(battle.getVariables().getPlayer2Stages().getDefStage() > 0 && hits){
			return "Foe's Defense rose. ";
		}
		else if(battle.getVariables().getPlayer2Stages().getDefStage() < 0 && hits){
			return "Foe's Defense fell. ";
		}
		return "";
	}

	public String checkSpAtkStat(boolean hits){
		if(battle.getVariables().getPlayer1Stages().getSpatkStage() > 0 && hits){
			return "Player's Special Attack rose. ";
		}
		else if(battle.getVariables().getPlayer1Stages().getSpatkStage() < 0 && hits){
			return "Player's Special Attack fell. ";
		}

		if(battle.getVariables().getPlayer2Stages().getSpatkStage() > 0 && hits){
			return "Foe's Special Attack rose. ";
		}
		else if(battle.getVariables().getPlayer2Stages().getSpatkStage() < 0 && hits){
			return "Foe's Special Attack fell. ";
		}
		return "";
	}
	
	public String checkSpDefStat(boolean hits){
		if(battle.getVariables().getPlayer1Stages().getSpdefStage() > 0 && hits){
			return "Player's Special Defense rose. ";
		}
		else if(battle.getVariables().getPlayer1Stages().getSpdefStage() < 0 && hits){
			return "Player's Special Defense fell. ";
		}

		if(battle.getVariables().getPlayer2Stages().getSpdefStage() > 0 && hits){
			return "Foe's Special Defense rose. ";
		}
		else if(battle.getVariables().getPlayer2Stages().getSpdefStage() < 0 && hits){
			return "Foe's Special Defense fell. ";
		}
		return "";
	}
	
	public String checkSpdStat(boolean hits){
		if(battle.getVariables().getPlayer1Stages().getSpdStage() > 0 && hits){
			return "Player's Speed rose. ";
		}
		else if(battle.getVariables().getPlayer1Stages().getSpdStage() < 0 && hits){
			return "Player's Speed fell. ";
		}

		if(battle.getVariables().getPlayer2Stages().getSpdStage() > 0 && hits){
			return "Foe's Speed rose. ";
		}
		else if(battle.getVariables().getPlayer2Stages().getSpdStage() < 0 && hits){
			return "Foe's Speed fell. ";
		}
		return "";
	}
	
	public String checkAccStat(boolean hits){
		if(battle.getVariables().getPlayer1Stages().getAccuracyStage() > 0 && hits){
			return "Player's Accuracy rose. ";
		}
		else if(battle.getVariables().getPlayer1Stages().getAccuracyStage() < 0 && hits){
			return "Player's Accuracy fell. ";
		}

		if(battle.getVariables().getPlayer2Stages().getAccuracyStage() > 0 && hits){
			return "Foe's Accuracy rose. ";
		}
		else if(battle.getVariables().getPlayer2Stages().getAccuracyStage() < 0 && hits){
			return "Foe's Accuracy fell. ";
		}
		return "";
	}
	
	public String checkEvsStat(boolean hits){
		if(battle.getVariables().getPlayer1Stages().getEvasionStage() > 0 && hits){
			return "Player's Evasion rose. ";
		}
		else if(battle.getVariables().getPlayer1Stages().getEvasionStage() < 0 && hits){
			return "Player's Evasion fell. ";
		}

		if(battle.getVariables().getPlayer2Stages().getEvasionStage() > 0 && hits){
			return "Foe's Evasion rose. ";
		}
		else if(battle.getVariables().getPlayer2Stages().getEvasionStage() < 0 && hits){
			return "Foe's Evasion fell. ";
		}
		return "";
	}
	
	
}