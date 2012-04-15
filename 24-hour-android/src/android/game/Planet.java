package android.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Planet extends Sprite {
	Vector3D p;
	double r;
	double m;
	type t = type.NEUTRAL;
	
	enum type{
		WIN,
		FRIENDLY,
		HOSTILE,
		DESERT,
		NEPTUNE,
		NEUTRAL
	}
	
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
	
	public Planet(String filename, Vector3D position, double radius, double mass, double rotation) {
		super(filename, rotation);
		this.p = position;
		this.r = radius;
		this.m = mass == 0 ? Math.PI * radius * radius / 50 : mass;
	}
		
	public Planet(String filename, type t, Vector3D position, double radius, double mass, double rotation) {
		super(filename, rotation);
		this.p = position;
		this.r = radius;
		this.m = mass;
		this.t = t;
	}
	
	public Planet(String filename, type t, Vector3D position, double radius, double rotation) {
		super(filename, rotation);
		this.p = position;
		this.r = radius;
		this.m = Math.PI * radius * radius / 50;
		this.t = t;
	}
	
	public void draw(SpriteBatch batch, int dx, int dy){
		x = p.getX();
		y = p.getY();
		width = height = (int) r * 2;
		
		batch.draw(texture, (int) (x - dx - r), (int) (y - dy - r), width / 2, height / 2, width, height, 1, 1, (float) rotation);
	}
}
