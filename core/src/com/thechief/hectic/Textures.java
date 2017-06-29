package com.thechief.hectic;

import com.badlogic.gdx.graphics.Texture;

public class Textures {

	private Textures() {
	}

	public static Texture test;
	public static Texture player1, player2;
	public static Texture spawner;
	public static Texture gun1;
	public static Texture bullet;
	public static Texture enemy1, enemy2;
	public static Texture[] explosion;
	public static Texture health;
	public static Texture meteor1, meteor2;
	public static Texture particle;

	public static final void INIT_ALL() {
		// Y U USE JPG 4 BREH
		test = new Texture("badlogic.jpg");

		player1 = new Texture("player1.png");
		player2 = new Texture("player2.png");

		enemy1 = new Texture("enemy1.png");
		enemy2 = new Texture("enemy2.png");

		spawner = new Texture("spawner.png");

		gun1 = new Texture("gun.png");
		bullet = new Texture("bullet.png");

		explosion = new Texture[10];

		explosion[0] = new Texture("explosion1.png");
		explosion[1] = new Texture("explosion2.png");
		explosion[2] = new Texture("explosion3.png");
		explosion[3] = new Texture("explosion4.png");
		explosion[4] = new Texture("explosion5.png");
		explosion[5] = new Texture("explosion6.png");
		explosion[6] = new Texture("explosion7.png");
		explosion[7] = new Texture("explosion8.png");
		explosion[8] = new Texture("explosion9.png");
		explosion[9] = new Texture("explosion10.png");

		health = new Texture("health.png");

		meteor1 = new Texture("meteor1.png");
		meteor2 = new Texture("meteor2.png");

		particle = new Texture("particle.png");
	}

	public static final void DISPOSE_ALL() {
		test.dispose();
		player1.dispose();
		player2.dispose();
		enemy1.dispose();
		enemy2.dispose();
		spawner.dispose();
		gun1.dispose();
		bullet.dispose();
		health.dispose();
		meteor1.dispose();
		meteor2.dispose();
		particle.dispose();
		for (int i = 0; i < explosion.length; i++) {
			explosion[i].dispose();
		}
	}

}
