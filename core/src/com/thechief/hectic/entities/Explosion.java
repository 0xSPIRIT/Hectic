package com.thechief.hectic.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Sounds;
import com.thechief.hectic.Textures;
import com.thechief.hectic.graphics.Animation;
import com.thechief.hectic.states.GameState;

public class Explosion extends Entity {

	private Animation anim;
	private GameState gs;

	public Explosion(GameState gs, Vector2 pos, int width, int height) {
		super(Textures.explosion[0], pos, width, height);
		this.gs = gs;
		anim = new Animation(this, Textures.explosion, 3, width, height);
	}

	@Override
	public void update(float dt) {
		gs.shakeScreen(20, 0.9f);
		anim.update();
		if (anim.index == 3) {
			gs.entities.removeValue(this, false);
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		anim.render(sb);
	}

}
