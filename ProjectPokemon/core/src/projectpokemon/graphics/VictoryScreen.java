package projectpokemon.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class VictoryScreen extends ScreenAdapter{

	private Stage stage;
	private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	
	private TextButton exit;

	private Sprite background;

	public VictoryScreen(){
		initStage();
		initBackground();
		initMainMenuButton();
	}

	@Override
	public void render (float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
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
		stage.dispose();
		skin.dispose();
		background.getTexture().dispose();
	}

	public void initStage(){
		stage = new Stage(new StretchViewport(1920, 1080));
		Gdx.input.setInputProcessor(stage);
		Engine.getInstance().stopBgm();
		Engine.getInstance().setSfx("victory");
		
	}
	
	public void initBackground(){
		background = new Sprite(new Texture(Gdx.files.internal("./backgrounds/victory.jpg")));
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
	}
	
	public void initMainMenuButton(){
		exit = new TextButton("Main Menu", skin);
		exit.setSize(600, 150);
		exit.setPosition(200, 400);
		exit.getLabel().setFontScale(2.5f);
		
		exit.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						Engine.getInstance().getInfo().reset();
						Engine.getInstance().setSfx("click");
						ScreenFactory screens = ScreenFactory.getInstance();
						screens.getGame().setScreen(screens.getMainMenuScreen());
					}
				}
				);
		stage.addActor(exit);
	}
}
