package android.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnimationSprite extends Sprite{
	ArrayList<Sprite> frames;
	
//	double x, y, w, h, r;
	
	int curFrame;
	int frameLength;
	int frameCount;
	
	public AnimationSprite(String filename, int frameLength) {
		super(filename);
		
		frames = new ArrayList<Sprite>();
		frames.add(new Sprite(filename));
		
		this.frameLength = frameLength;
	}
	
	public void addFrame(Sprite frame){
		frames.add(frame);
	}
	
	public void draw(SpriteBatch model, int dx, int dy){
		frameCount += 1;
		curFrame = (frameCount / frameLength) % frames.size();
		Sprite sprite = frames.get(curFrame);
		
		sprite.x = x;
		sprite.y = y;
		sprite.width = width;
		sprite.height = height;
		sprite.rotation = rotation;
		sprite.draw(model, dx, dy);
	}
}
