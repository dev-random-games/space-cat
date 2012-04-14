package android.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LibgdxTest implements ApplicationListener{
	
	private Stage stage;
	private SpriteBatch batch;
	Controller controller;
	GestureDetector gestureDetector;
	
	Player player;
	ArrayList<Planet> planets;
	
	Sprite menuBar;
	
	Audio audio;
	
	int x, y;

	public void create() {
		stage = new Stage(0, 0, true);
		batch = new SpriteBatch();
		
		controller = new Controller();
        controller.model = this;
        gestureDetector = new GestureDetector(controller);
        Gdx.input.setInputProcessor(gestureDetector);
        planets = new ArrayList<Planet>();
        
        player = new Player();
        player.v = new Vector3D(1f, 2f, 0);
        planets.add(new Planet("sun.png", new Vector3D(300, 200, 0), 100, 500));
        planets.add(new Planet("sun.png", new Vector3D(500, 100, 0), 150, 1000));
        
        menuBar = new Sprite("menubar.png");
        menuBar.height = 50;
        menuBar.width = Gdx.graphics.getWidth();
        

	}

	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		player.move();
		
		x = (int) player.x - Gdx.graphics.getWidth() / 2;
		y = (int) player.y - Gdx.graphics.getHeight() / 2;
		
		for (Planet planet : planets){
			planet.draw(batch, x, y);
			player.influence(planet);
			
		}
		
		player.draw(batch, x, y);
		
		menuBar.x = x;
		menuBar.y = y;
		menuBar.draw(batch, x, y);
		
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
        audio = Gdx.audio;
        Sound bambi = audio.newSound(Gdx.files.internal("bambi.ogg"));
        bambi.play(100);
	}

	public void dispose() {
		stage.dispose();
	}

	public void pause() {
		
	}
	
	public void resume() {
		
	}

}
