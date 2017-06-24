package com.thechief.hectic.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.hectic.Textures;

public class Player extends Entity {

	// Texture Animations
	private long lastTime;
	private Array<Texture> textures;
	private int index = 0;
	
	private float spd;
	
	public Player(Vector2 pos, int width, int height) {
		super(Textures.player1, pos, width, height);
		lastTime = System.currentTimeMillis();
		textures = new Array<Texture>();
		textures.add(Textures.player1);
		textures.add(Textures.player2);
		
		spd = 400;
	}

	@Override
	public void update(float dt) {
		animate();
		
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			pos.x += spd * dt;
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			pos.x -= spd * dt;
		}
	}

	private void animate() {
		if (System.currentTimeMillis() - lastTime > 100) {
			if (index < textures.size - 1) {
				index++;
			} else if (index == textures.size - 1) {
				index = 0;
			}
			lastTime = System.currentTimeMillis();
		}
		
		texture = textures.get(index);
	}
	
}
