package com.thechief.hectic.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Main;
import com.thechief.hectic.Textures;
import com.thechief.hectic.states.GameState;

public class Spawner extends Entity {

	private int time = 0;
	private int createInterval = 15;
	private GameState gs;
	
	public Spawner(GameState gs, Vector2 pos, int width, int height) {
		super(Textures.spawner, pos, width, height);
		this.gs = gs;
	}

	@Override
	public void update(float dt) {
		time++;
		if (time % 15 == 0) {
			pos.x = MathUtils.random(0, Main.WIDTH);
		}
		if (time % createInterval == 0) {
			gs.enemies.add(new Enemy(gs, new Vector2(pos.x, pos.y), 64, 64));
			gs.entities.add(gs.enemies.get(gs.enemies.size - 1));
		}
	}

}
