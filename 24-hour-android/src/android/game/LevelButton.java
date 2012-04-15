package android.game;

public class LevelButton extends Button{
	
	int levelNum;

	public LevelButton(int x, int y, int width, int height, boolean scale, int levelNum) {
		super(x, y, width, height, scale);
		this.levelNum = levelNum;
	}

	@Override
	public void react(LibgdxTest model) {
		model.loadLevel(levelNum);
		model.menuMode = false;
	}

}
