package android.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlackHole extends Planet{
	public BlackHole(String filename, Vector3D position, double radius, double mass, double rotation) {
		super(filename, Planet.type.HOSTILE, position, radius, mass, rotation);
	}
	
	public void draw(SpriteBatch batch, int dx, int dy) {
		x = p.getX();
		y = p.getY();
		width = height = (int) r * 2;
		
		batch.draw(texture, (int) (x - dx - r), (int) (y - dy - r), width / 2, height / 2, width, height, 1, 1, (float) this.rotation);
	
		this.rotation = (this.rotation + 1) % 360; 
	}
}
