package android.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends AnimationSprite{

	Vector3D p, v, a;
	
	Sprite arrow;
	
	double fuel;
	double maxFuel = 200;
	boolean refill = false;
	
	boolean launchMode = true;
	int launchModeCoolDown = 0;
	int maxLaunchModeCoolDown = 20;
	
	boolean isVisible = true;
	
	public Player() {
		super("cat1.png", 1);
		addFrame(new Sprite("cat2.png"));
		addFrame(new Sprite("cat3.png"));
		addFrame(new Sprite("cat4.png"));
		addFrame(new Sprite("cat5.png"));
		addFrame(new Sprite("cat6.png"));
		addFrame(new Sprite("cat7.png"));
		addFrame(new Sprite("cat8.png"));
		addFrame(new Sprite("cat9.png"));
		addFrame(new Sprite("cat10.png"));
		addFrame(new Sprite("cat9.png"));
		addFrame(new Sprite("cat8.png"));
		addFrame(new Sprite("cat7.png"));
		addFrame(new Sprite("cat6.png"));
		addFrame(new Sprite("cat5.png"));
		addFrame(new Sprite("cat4.png"));
		addFrame(new Sprite("cat3.png"));
		addFrame(new Sprite("cat2.png"));
		v = new Vector3D();
        p = new Vector3D();
        a = new Vector3D();
        
        fuel = maxFuel;
        
        arrow = new Sprite("pointer.png");
        arrow.rotCenter = false;
        arrow.width = arrow.height = 60;
	}
	
	public void move(){
		a = a.scale(.9);
		if (fuel > 0){
			fuel -= a.length() * 5;
		} else {
			fuel = 0;
		}
		v = v.add(a);
		p = p.add(v);
		launchModeCoolDown -= 1;
	}
	
//<<<<<<< HEAD
//	public void draw(SpriteBatch batch, int dx, int dy){
//		x = p.getX();
//		y = p.getY();
//		
//		rotation = - 360 * Math.atan2(v.getX(), v.getY()) / (2 * Math.PI);
//		super.draw(batch, dx, dy);
//		if (refill){
//			if (fuel < maxFuel){
//				fuel += 10;
//			} else {
//				fuel = maxFuel;
//				refill = false;
//=======
	public void draw(SpriteBatch batch, int dx, int dy, int winX, int winY){
		if (this.isVisible) {
			x = p.getX();
			y = p.getY();

			Vector3D toWin = new Vector3D(winX - x, winY - y, 0).normalize();
			arrow.rotation = - 360 * Math.atan2(toWin.getX(), toWin.getY()) / (2 * Math.PI) + 45;

			Vector3D wallIntersect = LibgdxTest.vectorIntersectionWithRectangle(new Vector3D(x, y, 0), toWin, dx, dy, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

			Vector3D arrowPos = wallIntersect.subtract(toWin.scale(100));

			arrow.x = arrowPos.getX();//p.getX() + width / 2 + toWin.getX() * 100;
			arrow.y = arrowPos.getY();//p.getY() + height / 2 + toWin.getY() * 100;
			//		arrow.draw(batch, dx, dy);

			rotation = - 360 * Math.atan2(v.getX(), v.getY()) / (2 * Math.PI);
			super.draw(batch, dx, dy);
			if (refill){
				if (fuel < maxFuel){
					fuel += 10;
				} else {
					fuel = maxFuel;
					refill = false;
				}
			}
		}
	}
	
	/*
	 * Influence the cat with gravity. Returns true if collision occurs
	 * Returns 1 if win
	 * Returns 2 if lose
	 */
	public int influence(Planet planet){
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
			
			if (planet.t == Planet.type.WIN){
				return 1;
			} else if (planet.t == Planet.type.HOSTILE){
				this.isVisible = false;
				return 2;
			} else if (planet.t == Planet.type.FRIENDLY){
				refill = true;
				return 3;
			}
			
		}
		
		if (launchModeCoolDown > 0){
			v = v.add(dir.scale((float) (planet.m / connector.lengthSquared()) * (maxLaunchModeCoolDown - launchModeCoolDown) / maxLaunchModeCoolDown));
		} else {
			v = v.add(dir.scale((float) (planet.m / connector.lengthSquared())));
		}
		
		return 0;
	}

}
