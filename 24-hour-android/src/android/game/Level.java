package android.game;

import java.util.ArrayList;

public class Level {
	private Player player;
	private ArrayList<Planet> planets;
	
	public Level(Player player, ArrayList<Planet> planets) {
		this.player = player;
		this.planets = planets;
	}
}
