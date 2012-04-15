package android.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlackHole extends Planet{
	public BlackHole(String filename, Vector3D position, double radius, double mass, double rotation) {
		super(filename, Planet.type.HOSTILE, position, radius, mass, rotation);
	}
	
	public BlackHole(String filename, Vector3D position, double radius, double rotation) {
		super(filename, Planet.type.HOSTILE, position, radius, Math.PI * radius * radius / 25, rotation);
	}
	
	public void draw(SpriteBatch batch, int dx, int dy) {
		x = p.getX();
		y = p.getY();
		width = height = (int) r * 5;
		
		batch.draw(texture, (int) (x - dx - width / 2), (int) (y - dy - height / 2), width / 2, height / 2, width, height, 1, 1, (float) this.rotation);
	
		this.rotation = (this.rotation + 1) % 360; 
	}
}
