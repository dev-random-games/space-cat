package android.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Sprite{

	Vector3D p, v;
	
	public Player() {
		super("cat.png");
		v = new Vector3D();
        p = new Vector3D();
	}
	
	public void move(){
		p = p.add(v);
	}
	
	public void draw(SpriteBatch batch){
		x = p.getX();
		y = p.getY();
		rotation = - 360 * Math.atan2(v.getX(), v.getY()) / (2 * Math.PI);
		super.draw(batch);
	}

}
