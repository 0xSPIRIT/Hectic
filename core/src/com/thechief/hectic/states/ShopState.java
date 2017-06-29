package com.thechief.hectic.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.thechief.hectic.Main;

public class ShopState extends State {

	private ShapeRenderer sr;
	
	private int padding = 15;
	
	public ShopState(OrthographicCamera camera) {
		this.camera = camera;
	}

	@Override
	public void create() {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		sr = new ShapeRenderer();
	}

	@Override
	public void update(float dt) {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			StateManager.setCurrentState(new GameState());
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sr.setProjectionMatrix(camera.combined);
		sr.begin(ShapeType.Line);
		
		sr.setColor(Color.WHITE);
		sr.rect(padding, padding, Main.WIDTH - padding, Main.HEIGHT - padding);
		
		sr.end();
	}

	@Override
	public void dispose() {
		sr.dispose();
	}

}
