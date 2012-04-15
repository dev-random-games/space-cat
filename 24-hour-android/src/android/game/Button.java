package android.game;

public abstract class Button {
	
	int x, y, w, h;
	boolean scale;
	
	public Button (int x, int y, int width, int height, boolean scale){
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
		
		this.scale = scale;
	}
	
	public boolean pointIntersects(int x, int y){
		if (x > this.x && x < this.x + w && y > this.y && y < this.y + h){
			return true;
		} else {
			return false;
		}
	}
	
	public abstract void react(LibgdxTest model);
}
