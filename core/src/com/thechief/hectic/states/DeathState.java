package com.thechief.hectic.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.thechief.hectic.Fonts;
import com.thechief.hectic.Main;
import com.thechief.hectic.states.shop.ShopState;

public class DeathState extends State {

	private int score, highScore;
	private int time = 0;

	private String scoreString, highScoreString, shopInstructions;

	public DeathState(OrthographicCamera camera, int score, int highScore) {
		this.camera = camera;
		this.score = score;
		this.highScore = highScore;
		
		shopInstructions = "Press 'B' to enter the BLACK MARKET.";
	}

	@Override
	public void create() {
		scoreString = "SCORE: " + score;
		highScoreString = "\nHIGH SCORE: " + highScore;
	}

	@Override
	public void update(float dt) {
		time++;
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			StateManager.setCurrentState(new GameState());
			GameState.SCORE = 0;
		}
		if (Gdx.input.isKeyJustPressed(Keys.B)) {
			StateManager.setCurrentState(new ShopState(camera));
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
		Fonts.calibri.draw(sb, shopInstructions, Main.WIDTH / 2 - new GlyphLayout(Fonts.calibri, shopInstructions).width / 2, Main.HEIGHT - 40);
		Fonts.calibri.setColor(new Color(20, 20, 20, 20));
		Fonts.calibri.draw(sb, shopInstructions, Main.WIDTH / 2 - new GlyphLayout(Fonts.calibri, shopInstructions).width / 2 + 2, Main.HEIGHT - 40 - 2);
		
		Fonts.calibri.setColor(Color.BLACK);
		sb.end();
	}

	@Override
	public void dispose() {

	}

}
