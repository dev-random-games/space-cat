package android.game;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
	
	Audio audio;
	static Music bambi;
	
	Sprite fuel;
	
	Camera camera;
	
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
//        planets.add(new Planet("sun.png", new Vector3D(300, 200, 0), 100, 1000));
//        planets.add(new Planet("sun.png", new Vector3D(500, 100, 0), 150, 3000));
        
        /*
         * Creates planets
         */
        int scale = 750;
        Random random = new Random();
    	planets.add(new Planet("earth.png", new Vector3D(random.nextInt(1000), random.nextInt(1000), 0), random.nextInt(scale) * .1 + 20, 0, random.nextDouble() * 360));
        for (int i = 0; i < 5; i++){
        	int planetDecide = random.nextInt(100);
        	String planet;
        	if (planetDecide > 50){
        		planet = "sun.png";
        	} else if (planetDecide > 25){
        		planet = "neptune.png";
        	} else {
        		planet = "mars.png";
        	}
        	planets.add(new Planet(planet, new Vector3D(random.nextInt(1000), random.nextInt(1000), 0), random.nextInt(scale) * .05 + 20, 0, random.nextDouble() * 360));
        }
        
        audio = Gdx.audio;
        bambi = audio.newMusic(Gdx.files.internal("bambi.ogg"));
        //bambi.setLooping(true);
      
        fuel = new Sprite("fuelbar.png");
        fuel.height = 15;
        fuel.width = Gdx.graphics.getWidth();

        this.camera = new Camera(this.player);
	}

	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if (!player.launchMode){
			player.move();
			for (Planet planet : planets){
				player.influence(planet);
			}
		}
		
		batch.begin();

		this.camera.update();
		x = (int) camera.loc.getX();
		y = (int) camera.loc.getY();
		
		for (Planet planet : planets){
			planet.draw(batch, x, y);
		}
		
		player.draw(batch, x, y);
		
		fuel.width = (int) (Gdx.graphics.getWidth() * player.fuel / player.maxFuel);
		fuel.x = (int) ((Gdx.graphics.getWidth() - fuel.width) / 2);
		fuel.draw(batch, 0, 0);
		
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	public void dispose() {
		stage.dispose();
	}

	public void pause() {
		
	}
	
	public void resume() {
		
	}

}
