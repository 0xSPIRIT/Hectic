package com.thechief.hectic;

import com.badlogic.gdx.graphics.Texture;

public class Textures {

	private Textures() {}
	
	public static Texture player1, player2;
	public static Texture spawner;
	public static Texture gun1;
	public static Texture bullet;
	public static Texture enemy1, enemy2;
	public static Texture explosion1, explosion2, explosion3, explosion4;
	
	public static final void INIT_ALL() {
		player1 = new Texture("player1.png");
		player2 = new Texture("player2.png");
		
		enemy1 = new Texture("enemy1.png");
		enemy2 = new Texture("enemy2.png");
	
		spawner = new Texture("spawner.png");
		
		gun1 = new Texture("gun.png");
		bullet = new Texture("bullet.png");
		
		explosion1 = new Texture("explosion1.png");
		explosion2 = new Texture("explosion2.png");
		explosion3 = new Texture("explosion3.png");
		explosion4 = new Texture("explosion4.png");
	}
	
	public static final void DISPOSE_ALL() {
		player1.dispose();
		player2.dispose();
		enemy1.dispose();
		enemy2.dispose();
		spawner.dispose();
		gun1.dispose();
		bullet.dispose();
		explosion1.dispose();
		explosion2.dispose();
		explosion3.dispose();
		explosion4.dispose();
	}
	
}
