package com.thechief.hectic.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Textures;
import com.thechief.hectic.entities.Entity;
import com.thechief.hectic.entities.Meteorite;
import com.thechief.hectic.states.GameState;

public class Particle extends Entity {

	private float alpha = 1f;
	private float spd;
	private GameState gs;
	
	private float velX, velY;

	public Particle(GameState gs, Vector2 pos, Entity parent) {
		super(Textures.particle, pos);
		this.gs = gs;
		width = MathUtils.random(8, 32);
		height = MathUtils.random(8, 32);
		spd = MathUtils.random(0.001f, 0.03f);
		
		if (parent instanceof Meteorite) {
			Meteorite m = (Meteorite) parent;
			velX = -m.velX * 0.5f;
			velY = -m.velY * 0.5f;
		}
	}

	@Override
	public void update(float dt) {
		alpha -= spd;
		if (alpha < 0) {
			gs.entities.removeValue(this, false);
		}
		pos.x += velX;
		pos.y += velY;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(1, 1, 1, alpha);
		super.render(sb);
		sb.setColor(1, 1, 1, 1);
	}

}
