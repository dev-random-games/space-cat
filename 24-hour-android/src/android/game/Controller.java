package android.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class Controller implements GestureListener{
	
	LibgdxTest model;
	
	boolean touchDown = false;

	@Override
	public boolean fling(float vX, float vY) {
		LibgdxTest.bambi.play();
		
		vY = -vY;
		
		int launchScale = 500;
		
		if (model.player.launchMode){
			model.player.v = new Vector3D(vX / launchScale, vY / launchScale, 0);
			model.player.launchMode = false;
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
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer) {
		y = Gdx.graphics.getHeight() - y;
		touchDown = true;
		
		return false;
	}

	@Override
	public boolean zoom(float distOrig, float distNew) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
