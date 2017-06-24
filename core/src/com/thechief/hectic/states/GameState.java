package com.thechief.hectic.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.hectic.Main;
import com.thechief.hectic.entities.Entity;
import com.thechief.hectic.entities.Player;

public class GameState extends State {

	private Player player;
	private Array<Entity> entities = new Array<Entity>();
	
	@Override
	public void create() {
		setUp(Main.WIDTH, Main.HEIGHT);
		player = new Player(new Vector2(camera.position.x - 32, 0), 64, 64);
		entities.add(player);
	}

	@Override
	public void update(float dt) {
		for (Entity e : entities) {
			e.update(dt);
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
