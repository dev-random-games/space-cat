package android.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Controller implements GestureListener{
	
	LibgdxTest model;
	
	boolean touchDown = false;
	Vector3D touchSpot;
	
	double levelStartTime;
	
	int launchScale = 500;
	int fuelScale = 5000;

	@Override
	public boolean fling(float vX, float vY) {
		
		vY = -vY;
		
		if (touchDown){
			if (model.player.launchMode){
				model.player.v = new Vector3D(vX / launchScale, vY / launchScale, 0);
				model.player.launchMode = false;
			} else {
				if (model.player.fuel > 0){
					model.player.a = new Vector3D(vX / fuelScale, vY / fuelScale, 0);
					
					Random random = new Random();
					
					switch (random.nextInt(5)){
					case 0:
						LibgdxTest.fart0.play();
						break;
					case 1:
						LibgdxTest.fart1.play();
						break;
					case 2:
						LibgdxTest.fart2.play();
						break;
					case 3:
						LibgdxTest.thbb0.play();
						break;
					case 4:
						LibgdxTest.thbb1.play();
						break;
					default:
						break;
					}
					
				} else {
					model.player.fuel = 0;
				}
			}
		}
		touchDown = false;
		return false;
	}

	@Override
	public boolean longPress(int x, int y) {
		y = Gdx.graphics.getHeight() - y;
		return false;
	}

	@Override
	public boolean pan(int x, int y, int dx, int dy) {
		y = Gdx.graphics.getHeight() - y;
		if (touchDown){
//			model.player.v = model.player.p.subtract(new Vector3D(x, y, 0)).normalize().scale(-3);
		}
		
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {
		double initWidth = Math.abs(initialFirstPointer.x - initialSecondPointer.x);
		double initHeight = Math.abs(initialFirstPointer.y - initialSecondPointer.y);
		double width = Math.abs(firstPointer.x - secondPointer.x);
		double height = Math.abs(firstPointer.y - secondPointer.y);
		double dW = width - initWidth;
		double dH = height - initHeight;
		double dSize = dW + dH;
		
		return false;
	}

	@Override
	public boolean tap(int x, int y, int count) {
		y = Gdx.graphics.getHeight() - y;
		
//		model.player.v = model.player.p.subtract(new Vector3D(x, y, 0)).normalize().scale(-3);
		
		if (model.menuButton.intersects(new Rectangle(x - 5, y - 5, 10, 10))){
			model.menuMode = true;
		}
		
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer) {
		y = Gdx.graphics.getHeight() - y;
		if (!model.menuMode){
			touchDown = true;
			touchSpot = new Vector3D(x, y, 0);
		} else {
			model.currentMenu.touch(x, y, model);
		}
		return false;
	}

	@Override
	public boolean zoom(float distOrig, float distNew) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
