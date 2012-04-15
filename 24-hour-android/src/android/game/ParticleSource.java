package android.game;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ParticleSource {
	
	protected TextureRegion texture;
	int halfLife;
	int numParticles;
	int explodeVelocity;
	Vector3D initVelocity;
	Vector3D centerPosition;
	ArrayList<Particle> particles;
	int maxInitDisplacement;
	int width;
	int height;
	
	public ParticleSource(int halfLife, int numParticles, double maxExplodeSpeed, Vector3D initVelocity, Vector3D initPosition, int maxInitDisplacement, String filename) {
		Random r = new Random();
		texture = new TextureRegion(new Texture(Gdx.files.internal(filename)));
		width = texture.getRegionWidth();
		height = texture.getRegionHeight();
		particles = new ArrayList<Particle>();
		this.centerPosition = initPosition;
		this.halfLife = halfLife;
		this.numParticles = numParticles;
		this.explodeVelocity = explodeVelocity;
		this.initVelocity = initVelocity;
		this.maxInitDisplacement = maxInitDisplacement;
		for (int i = 0; i < numParticles; i++) {
			Vector3D pos = new Vector3D(r.nextInt(2000) - 1000, r.nextInt(2000) - 1000, 0);
			pos = pos.normalize();
			//Vector3D vel = new Vector3D(r.nextInt(2000) - 1000, r.nextInt(2000) - 1000, 0);
			Vector3D vel = pos.add(new Vector3D());
			pos = pos.scale(r.nextDouble() * maxInitDisplacement);
			vel = vel.scale(maxExplodeSpeed);
			particles.add(new Particle(pos, vel, r.nextInt(halfLife)));
		}
	}
	
	public boolean draw(SpriteBatch batch, int dx, int dy) { // Returns true if this object should stay alive, false if it's dead
		centerPosition = centerPosition.add(initVelocity);
		boolean anyAlive = false;
		int aliveCount = 0;
		for (int i = 0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			p.tick();
			if (p.shouldRender()) {
				batch.draw(texture, (float) (centerPosition.getX() + p.position.getX() - dx), (float) (centerPosition.getY() + p.position.getY() - dy), 0, 0, width, height, 1, 1, (float) p.rotation);
				anyAlive = true;
				aliveCount ++;
			} else {
				particles.remove(i);
				i--;
			}
		}
		return anyAlive;
	}
}
