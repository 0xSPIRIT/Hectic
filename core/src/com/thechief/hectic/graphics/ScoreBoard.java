package com.thechief.hectic.graphics;

import static com.thechief.hectic.states.GameState.HIGH_SCORE;
import static com.thechief.hectic.states.GameState.SCORE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Fonts;
import com.thechief.hectic.Textures;
import com.thechief.hectic.entities.Entity;
import com.thechief.hectic.states.DeathState;
import com.thechief.hectic.states.GameState;
import com.thechief.hectic.states.StateManager;

public class ScoreBoard extends Entity {

	private OrthographicCamera camera;
	private FileHandle highScoreFile;

	public ScoreBoard(OrthographicCamera camera, Vector2 pos) {
		super(Textures.test, pos);
		this.camera = camera;
	}

	@Override
	public void update(float dt) {
		SCORE++;
		if (SCORE > HIGH_SCORE) {
			HIGH_SCORE = SCORE;
		}
		if (GameState.DIED) {
			StateManager.setCurrentState(new DeathState(camera, SCORE, HIGH_SCORE));
			GameState.DIED = false;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		Fonts.calibri.draw(sb, "Score: " + SCORE + "\nHigh Score: " + HIGH_SCORE, pos.x, pos.y);
	}

}
