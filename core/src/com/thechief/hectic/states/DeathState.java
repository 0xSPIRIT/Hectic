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

	// if you need it uncomment it
	//private int score, highScore;
	private int time = 0;

	private CharSequence scoreString, highScoreString;

	public DeathState(OrthographicCamera camera, int score, int highScore) {
		this.camera = camera;
//		this.score = score;
//		this.highScore = highScore;
		scoreString = "SCORE: " + score;
		highScoreString = "\nHIGH SCORE: " + highScore;
	}

	@Override
	public void create() {

	}

	@Override
	public void update(float dt) {
		time++;
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			StateManager.setCurrentState(new GameState());
			GameState.SCORE = 0;
		}
	}

	private int r = MathUtils.random(Main.WIDTH - 256), c = MathUtils.random(Main.HEIGHT - 32);
	
	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		if (time % 30 == 0)
			Gdx.gl.glClearColor(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
		Fonts.calibri.setColor(Color.WHITE);

		Fonts.calibri.draw(sb, scoreString, r, c);
		Fonts.calibri.draw(sb, highScoreString, r, c + 64);
		
		Fonts.calibri.setColor(Color.BLACK);
		sb.end();
	}

	@Override
	public void dispose() {

	}

}
