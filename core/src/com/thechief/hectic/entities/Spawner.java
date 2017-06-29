package com.thechief.hectic.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Main;
import com.thechief.hectic.Textures;
import com.thechief.hectic.states.GameState;

public class Spawner extends Entity {

	private int time = 0;
	private float createInterval = 15;
	private float meteorInterval = 240;
	private GameState gs;

	private Vector2 startPos;
	
	public Spawner(GameState gs, Vector2 pos, int width, int height) {
		super(Textures.spawner, pos, width, height);
		this.gs = gs;
		startPos = pos;
	}

	@Override
	public void update(float dt) {
		time++;
		if (time % createInterval == 0) {
			pos.x = MathUtils.random(0, Main.WIDTH);
			gs.enemies.add(new Enemy(gs, new Vector2(pos.x, pos.y), 64, 64));
			gs.entities.add(gs.enemies.get(gs.enemies.size - 1));
		}
		if (time % meteorInterval == 0) {
			gs.meteors.add(new Meteorite(gs, new Vector2(MathUtils.random(Main.WIDTH), Main.HEIGHT + 20), 64, 64, MathUtils.random(-5f, 5f), MathUtils.random(-5f, 0f)));
			gs.entities.add(gs.meteors.get(gs.meteors.size - 1));
		}
		gs.getPlayer().setSpeed(gs.getPlayer().getSpeed() + 0.05f);
	}

}
