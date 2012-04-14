package android.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class Controller implements GestureListener{
	
	LibgdxTest model;
	
	boolean selected = false;

	@Override
	public boolean fling(float vX, float vY) {
		vY = -vY;
		selected = false;
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
		if (selected){
			model.x += dx;
			model.y -= dy;
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
		
		model.bambi.width += dSize / 100;
		model.bambi.height += dSize / 100;
		
		return false;
	}

	@Override
	public boolean tap(int x, int y, int count) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer) {
		y = Gdx.graphics.getHeight() - y;
		if (Math.abs(x - model.x) < 100 && Math.abs(y - model.y) < 100){
			selected = true;
		}
		return false;
	}

	@Override
	public boolean zoom(float distOrig, float distNew) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
