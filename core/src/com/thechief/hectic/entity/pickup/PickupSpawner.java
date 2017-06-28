package com.thechief.hectic.entity.pickup;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Main;
import com.thechief.hectic.Textures;
import com.thechief.hectic.entities.Entity;
import com.thechief.hectic.states.GameState;

public class PickupSpawner extends Entity {

	private long lastTime;
	private GameState gs;
	
	public PickupSpawner(GameState gs) {
		super(Textures.test, Vector2.Zero);
		lastTime = System.currentTimeMillis();
		this.gs = gs;
	}

	@Override
	public void update(float dt) {
		if (System.currentTimeMillis() - lastTime > 3000) {
			gs.entities.add(new Health(3, new Vector2(MathUtils.random(Main.WIDTH), MathUtils.random((int) (Main.HEIGHT / 2.5))), gs, 48, 48));
			lastTime = System.currentTimeMillis();
		}
	}
	
	@Override
	public void render(SpriteBatch sb) {
		// DO NATING
	}
	
}
