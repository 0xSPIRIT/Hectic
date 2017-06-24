package com.thechief.hectic.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {

	protected OrthographicCamera camera;
	
	protected void setUp(int screenWidth, int screenHeight) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, screenWidth, screenHeight);
	}
	
	public abstract void create();
	
	public abstract void update(float dt);
	
	public abstract void render(SpriteBatch sb);
	
	public abstract void dispose();

	// GETTERS:
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
}
