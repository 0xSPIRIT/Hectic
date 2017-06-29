package com.thechief.hectic.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.thechief.hectic.Fonts;
import com.thechief.hectic.Main;

public class DeathState extends State {

	private int score, highScore;
	private int time = 0;

	public DeathState(OrthographicCamera camera, int score, int highScore) {
		this.camera = camera;
		this.score = score;
		this.highScore = highScore;
	}

	@Override
	public void create() {

	}

	@Override
	public void update(float dt) {
		time++;
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			StateManager.setCurrentState(new GameState());
			GameState.SCORE = 0;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		if (time % 30 == 0)
			Gdx.gl.glClearColor(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
		Fonts.calibri.setColor(Color.WHITE);
		Fonts.calibri.draw(sb, "(place here an odd quote from a person you have never heard of before.)", camera.position.x / 2 - 125, Main.HEIGHT - 50);
		Fonts.calibri.draw(sb, "     SCORE: " + score + "\nHIGH SCORE: " + highScore, camera.position.x - 105, camera.position.y);
		Fonts.calibri.setColor(Color.BLACK);
		sb.end();
	}

	@Override
	public void dispose() {

	}

}
