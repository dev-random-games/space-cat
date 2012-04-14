package android.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Sprite{

	Vector3D p, v;
	
	public Player() {
		super("cat.png");
	}
	
	public void move(){
		p = p.add(v);
	}
	
	public void draw(SpriteBatch batch){
		super.draw(batch);
	}

}
