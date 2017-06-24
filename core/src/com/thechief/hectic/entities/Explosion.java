package com.thechief.hectic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Textures;
import com.thechief.hectic.graphics.Animation;
import com.thechief.hectic.states.GameState;

public class Explosion extends Entity {

	private Animation anim;
	private GameState gs;

	public Explosion(GameState gs, Vector2 pos, int width, int height) {
		super(Textures.explosion1, pos, width, height);
		Texture[] textures = new Texture[4];
		textures[0] = Textures.explosion1;
		textures[1] = Textures.explosion2;
		textures[2] = Textures.explosion3;
		textures[3] = Textures.explosion4;
		this.gs = gs;
		anim = new Animation(this, textures, 3, 64, 64);
	}

	@Override
	public void update(float dt) {
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
