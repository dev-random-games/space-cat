package android.game;

public class Particle {
	
	Vector3D position;
	Vector3D velocity;
	int lifetime;
	double rotation;
	
	public Particle(Vector3D position, Vector3D velocity, int lifetime) {
		this.position = position;
		this.velocity = velocity;
		this.lifetime = lifetime;
		this.rotation = Math.atan2(velocity.getY(), velocity.getX());
	}
	
	public boolean shouldRender() {
		return lifetime > 0;
	}
	
	public void tick() {
		position = position.add(velocity);
		lifetime--;
	}
}
