package com.thechief.hectic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Main;
import com.thechief.hectic.Textures;
import com.thechief.hectic.graphics.Animation;
import com.thechief.hectic.graphics.Particle;
import com.thechief.hectic.graphics.ScoreBoard;
import com.thechief.hectic.states.GameState;

public class Meteorite extends Entity {

	private GameState gs;

	private Texture[] textures;
	private Animation anim;

	public float velX, velY;

	private long lastTime;

	public Meteorite(GameState gs, Vector2 pos, int width, int height, float vx, float vy) {
		super(Textures.meteor1, pos, width, height);
		textures = new Texture[2];
		textures[0] = Textures.meteor1;
		textures[1] = Textures.meteor2;
		anim = new Animation(this, textures, 10);
		velX = vx;
		velY = vy;
		this.gs = gs;
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void update(float dt) {
		anim.update();
		for (int i = 0; i < gs.entities.size; i++) {
			Entity e = gs.entities.get(i);
			if (e instanceof ScoreBoard || e instanceof Meteorite) continue;
			if (e instanceof Spawner) {
				e.vibrateX(-5, 5);
				continue;
			}
			e.vibrate(-5, 5);
		}
		vibrateX(-10, 10);
		vibrateY(-8, 4);
		pos.x = MathUtils.clamp(pos.x, 0, Main.WIDTH - width);
		
		if (pos.x >= Main.WIDTH - width || pos.x <= 0) {
			velX *= -1;
		}
		if (pos.y <= height) {
			velY *= 1.5;
		}

		// 90 milliseconds
		if (System.currentTimeMillis() - lastTime > 50) {
			gs.entities.add(new Particle(gs, new Vector2((pos.x + width / 2) + MathUtils.random(-32, 32), (pos.y + height / 2) + MathUtils.random(-32, 32)), this));
			lastTime = System.currentTimeMillis();
		}

		if (pos.y <= 0) {
			Explosion ex = new Explosion(gs, new Vector2(pos.x, pos.y), 128, 128);
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
