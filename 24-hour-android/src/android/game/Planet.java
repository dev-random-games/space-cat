package android.game;

public class Planet extends Sprite {
	private Vector3D position;
	private double radius;
	private double mass;
	
	public Planet(String filename, Vector3D position, double radius, double mass) {
		super(filename);
		this.position = position;
		this.radius = radius;
		this.mass = mass;
	}
}
