package com.thechief.hectic;

import com.badlogic.gdx.graphics.Texture;

public class Textures {

	private Textures() {}
	
	public static Texture player1, player2;
	
	public static final void INIT_ALL() {
		player1 = new Texture("player1.png");
		player2 = new Texture("player2.png");
	}
	
	public static final void DISPOSE_ALL() {
		player1.dispose();
		player2.dispose();
	}
	
}
