package projectpokemon.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import projectpokemon.logic.InfoBattleAI;
import projectpokemon.logic.Trainer;

public class Engine{
	
	private static Engine instance = new Engine();
	
	private Trainer player = new Trainer();
	private InfoBattleAI info = new InfoBattleAI();
	
	private Sound sfx = null;
	private Music bgm = null;
	private float volume = 1.0f;
	
	private Engine(){}
	
	public static Engine getInstance(){
	      return instance;
	}
	
	public Trainer getPlayer() {
		return player;
	}
	
	public InfoBattleAI getInfo() {
		return info;
	}

	public void disposeSound(){
		if(this.bgm != null){
			this.bgm.stop();
			this.bgm.dispose();
		}
			
		if(this.sfx != null){
			this.sfx.stop();
			this.sfx.dispose();
		}
	}

	public void setBgm(String name) {
		if(this.bgm != null)
			this.bgm.dispose();
	    this.bgm = Gdx.audio.newMusic(Gdx.files.internal("./bgm/" + name + ".mp3"));
	    this.bgm.setVolume(volume);
	    this.bgm.setLooping(true);
	    this.bgm.play();
	}
	
	public void stopBgm() {
		if(this.bgm != null)
			this.bgm.stop();
	}

	public void setSfx(String name) {
		if(this.sfx != null)
			this.sfx.dispose();
	    this.sfx = Gdx.audio.newSound(Gdx.files.internal("./sfx/" + name + ".wav"));
	    this.sfx.setVolume(this.sfx.play(), volume);
	    
	}
		
	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
		if(this.bgm != null){
			this.bgm.setVolume(volume);
		}
	}

}

