package com.thechief.hectic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Textures;
import com.thechief.hectic.graphics.Animation;
import com.thechief.hectic.graphics.Particle;
import com.thechief.hectic.states.GameState;

public class Meteorite extends Entity {

	private GameState gs;

	private Texture[] textures;
	private Animation anim;

	public float velX, velY;

	private long lastTime;

	public Meteorite(GameState gs, Vector2 pos, int width, int height, float vx) {
		super(Textures.meteor1, pos, width, height);
		textures = new Texture[2];
		textures[0] = Textures.meteor1;
		textures[1] = Textures.meteor2;
		anim = new Animation(this, textures, 10);
		velX = vx;
		velY = -5f;
		this.gs = gs;
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void update(float dt) {
		gs.shakeScreen(50);

		anim.update();

		// 90 milliseconds
		if (System.currentTimeMillis() - lastTime > 50) {
			gs.entities.add(new Particle(gs, new Vector2((pos.x + width / 2) + MathUtils.random(-32, 32), (pos.y + height / 2) + MathUtils.random(-32, 32)), this));
			lastTime = System.currentTimeMillis();
		}

		if (pos.y <= 0) {
			Explosion ex = new Explosion(gs, new Vector2(pos.x, pos.y), 64, 64);
			gs.entities.add(ex);
			gs.meteors.removeValue(this, false);
			gs.entities.removeValue(this, false);
		}

		pos.x += velX;
		pos.y += velY;
	}

	@Override
	public void render(SpriteBatch sb) {
		anim.render(sb);
	}

}
