package android.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Sprite{

	Vector3D p, v;
	
	boolean launchMode = true;
	
	public Player() {
		super("cat.png");
		v = new Vector3D();
        p = new Vector3D();
	}
	
	public void move(){
		p = p.add(v);
	}
	
	public void draw(SpriteBatch batch, int dx, int dy){
		x = p.getX();
		y = p.getY();
		rotation = - 360 * Math.atan2(v.getX(), v.getY()) / (2 * Math.PI);
		super.draw(batch, dx, dy);
	}
	
	/*
	 * Influence the cat with gravity. Returns true if collision occurs
	 */
	public boolean influence(Planet planet){
		Vector3D connector = p.subtract(planet.p);
		Vector3D dir = connector.normalize().scale(-1);
		
		v = v.add(dir.scale((float) (planet.m / connector.lengthSquared())));
		
		return false;
	}

}
