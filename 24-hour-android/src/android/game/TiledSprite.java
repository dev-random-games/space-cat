package android.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TiledSprite extends Sprite {

	public TiledSprite(String filename) {
		super(filename);
	}
	
	public void draw(SpriteBatch batch, int dx, int dy){
		//batch.draw(texture, (int) x - dx, (int) y - dy, width / 2, height / 2, width, height, 1, 1, (float) rotation);
		int below = dy % height;
		int left = dx % width;
		for (int right=-1; right<2; right++) {
			for (int up=-3; up<3; up++) {
				super.draw(batch, left + right*width, below - up*height);
			}
		}
	}

}
