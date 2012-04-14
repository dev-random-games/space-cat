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
	
	public Planet(String filename, Vector3D position, double radius) {
		super(filename);
		this.p = position;
		this.r = radius;
		this.m = Math.PI * radius * radius / 50;
	}
	
	public void draw(SpriteBatch batch, int dx, int dy){
		x = p.getX();
		y = p.getY();
		width = height = (int) r * 2;
		
		batch.draw(texture, (int) (x - dx - r), (int) (y - dy - r), width / 2, height / 2, width, height, 1, 1, (float) rotation);
	}
}
