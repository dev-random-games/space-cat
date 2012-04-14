package android.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Sprite{

	Vector3D p, v;
	
	double fuel;
	double maxFuel = 500;
	
	boolean launchMode = true;
	int launchModeCoolDown = 0;
	int maxLaunchModeCoolDown = 20;
	
	public Player() {
		super("cat.png");
		v = new Vector3D();
        p = new Vector3D();
        
        fuel = maxFuel;
	}
	
	public void move(){
		p = p.add(v);
		launchModeCoolDown -= 1;
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
		Vector3D p = this.p.add(new Vector3D(width / 2, height / 2, 0));
		Vector3D connector = p.subtract(planet.p);
		Vector3D dir = connector.normalize().scale(-1);
		int r = this.width / 2;
		
		double totalRadiusSquared = Math.pow(r + planet.r, 2);
		if (totalRadiusSquared > connector.lengthSquared() && launchModeCoolDown <= 0){
			launchMode = true;
			v = dir.scale(-1);
			p = planet.p.subtract(dir.scale((float) (planet.r + height)));
			launchModeCoolDown = maxLaunchModeCoolDown;
		}
		
		if (launchModeCoolDown > 0){
			v = v.add(dir.scale((float) (planet.m / connector.lengthSquared()) * (maxLaunchModeCoolDown - launchModeCoolDown) / maxLaunchModeCoolDown));
		} else {
			v = v.add(dir.scale((float) (planet.m / connector.lengthSquared())));
		}
		
		return false;
	}

}
