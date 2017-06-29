package com.thechief.hectic.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Main;
import com.thechief.hectic.Textures;
import com.thechief.hectic.states.GameState;

public class Spawner extends Entity {

	private int time = 0;
	private float createInterval = 15;
	private float meteorInterval = 120;
	private GameState gs;

	public Spawner(GameState gs, Vector2 pos, int width, int height) {
		super(Textures.spawner, pos, width, height);
		this.gs = gs;
	}

	@Override
	public void update(float dt) {
		time++;
		System.out.println(createInterval);
		if (time % createInterval == 0) {
			pos.x = MathUtils.random(0, Main.WIDTH);
			gs.enemies.add(new Enemy(gs, new Vector2(pos.x, pos.y), 64, 64));
			gs.entities.add(gs.enemies.get(gs.enemies.size - 1));
		}
		if (time % meteorInterval == 0) {
			boolean facing = MathUtils.random(1) == 1; 
			gs.meteors.add(new Meteorite(gs, new Vector2(facing ? Main.WIDTH - 20 : 20, Main.HEIGHT - 100), 64, 64, facing ? -5f : 5f));
			gs.entities.add(gs.meteors.get(gs.meteors.size - 1));
		}
		gs.getPlayer().setSpeed(gs.getPlayer().getSpeed() + 0.05f);
	}

}
