package android.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Planet extends Sprite {
	Vector3D p;
	double r;
	double m;
	
	public Planet(String filename, Vector3D position, double radius, double mass) {
		super(filename);
		this.p = position;
		this.r = radius;
		this.m = mass;
	}
	
	public void draw(SpriteBatch batch, int dx, int dy){
		x = p.getX();
		y = p.getY();
		width = height = (int) r;
		
		super.draw(batch, dx, dy);
	}
}
