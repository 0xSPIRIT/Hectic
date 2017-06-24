package com.thechief.hectic.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.hectic.Main;
import com.thechief.hectic.entities.Enemy;
import com.thechief.hectic.entities.Entity;
import com.thechief.hectic.entities.Player;
import com.thechief.hectic.entities.Spawner;

public class GameState extends State {

	public static final float GRAVITY = -25f;

	private Player player;
	private Spawner spawner;

	public Array<Entity> entities = new Array<Entity>();
	public Array<Enemy> enemies = new Array<Enemy>();
	
	@Override
	public void create() {
		setUp(Main.WIDTH, Main.HEIGHT);
		player = new Player(this, new Vector2(camera.position.x - 32, 128), 64, 64);
		entities.add(player);
		spawner = new Spawner(this, new Vector2(camera.position.x - 32, Main.HEIGHT - 96), 64, 64);
		entities.add(spawner);
	}

	@Override
	public void update(float dt) {
		for (Entity e : entities) {
			e.update(dt);
		}

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			StateManager.setCurrentState(new GameState());
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		for (Entity e : entities) {
			e.render(sb);
		}
		sb.end();
	}

	@Override
	public void dispose() {

	}

}
