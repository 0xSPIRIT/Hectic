package com.thechief.hectic.entities;

import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Textures;
import com.thechief.hectic.states.GameState;

public class Bullet extends Entity {

	private float spd = 15f;
	private GameState gs;
	
	public Bullet(GameState gs, Vector2 pos, int width, int height) {
		super(Textures.bullet, pos, width, height);
		this.gs = gs;
	}

	@Override
	public void update(float dt) {
		pos.y += spd;
		
		for (int i = 0; i < gs.enemies.size; i++) {
			Enemy e = gs.enemies.get(i);
			if (isColliding(e)) {
				gs.enemies.removeValue(e, true);
				gs.entities.removeValue(e, false);
				Explosion explosion = new Explosion(gs, new Vector2(pos.x, pos.y), 64, 64);
				gs.entities.add(explosion);
				gs.entities.removeValue(this, false);
			}
		}
	}
	
}
