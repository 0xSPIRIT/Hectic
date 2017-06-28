package com.thechief.hectic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Textures;
import com.thechief.hectic.graphics.Animation;
import com.thechief.hectic.states.GameState;

public class Enemy extends Entity {

	private Animation anim;
	private float velY;
	private GameState gs;

	public Enemy(GameState gs, Vector2 pos, int width, int height) {
		super(Textures.enemy1, pos, width, height);
		this.gs = gs;
		Texture[] textures = new Texture[2];
		textures[0] = Textures.enemy1;
		textures[1] = Textures.enemy2;
		anim = new Animation(this, textures, 8, width, height);
		velY = 10f;
	}

	@Override
	public void update(float dt) {
		// Animation stuff
		anim.update();

		// GRAVITY IS A NEGATIVE NUMBER SO VELY IS GOING DOWN
		velY += GameState.GRAVITY * dt;

		pos.y += velY;

		if (pos.y <= 0) {
			Explosion explosion = new Explosion(gs, new Vector2(pos.x + 16, pos.y), 64, 64);
			gs.entities.add(explosion);
			gs.entities.removeValue(this, false);
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		anim.render(sb);
	}
	
}
