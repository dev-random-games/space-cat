package android.game;

import java.util.ArrayList;

import android.util.Log;

import com.badlogic.gdx.Gdx;

public class Menu extends Sprite{
	
	private ArrayList<Button> buttons;

	public Menu(String filename) {
		super(filename);
		
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		
		buttons = new ArrayList<Button>();
	}
	
	public void addButton(Button button){
		if (button.scale){
			button.x = button.x * width / imgWidth;
			button.y = button.y * height / imgHeight;
			button.w = button.w * width / imgWidth;
			button.h = button.h * height / imgHeight;
		}
		
		Log.d("Menu", "Button " + x + ", " + y);
		
		buttons.add(button);
	}
	
	public boolean touch(int x, int y, LibgdxTest model){
		Log.d("Menu", "touch: " + x + ", " + y);
		for (Button button : buttons){
			Log.d("Menu", button.x + ", " + button.y);
			if (button.pointIntersects(x, y)){
				button.react(model);
				return true;
			}
		}
		
		return false;
	}
}
