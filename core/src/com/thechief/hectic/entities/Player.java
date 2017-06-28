package com.thechief.hectic.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Fonts;
import com.thechief.hectic.Main;
import com.thechief.hectic.Textures;
import com.thechief.hectic.graphics.Animation;
import com.thechief.hectic.states.GameState;

public class Player extends Entity {

	private GameState gs;

	// Texture Animations
	private Texture[] textures;
	private Animation anim;

	private float spd;

	// Jumping
	private float jumpSpd = 690;
	private float velY = 0;

	// HEALTH
	public float maxHp = 20;
	public float hp = maxHp;

	public Player(GameState gs, Vector2 pos, int width, int height) {
		super(Textures.player1, pos, width, height);
		this.gs = gs;
		
		textures = new Texture[2];
		textures[0] = Textures.player1;
		textures[1] = Textures.player2;
		anim = new Animation(this, textures, 10);
		
		spd = 500;
	}

	@Override
	public void update(float dt) {
		// Animating the texture
		anim.update();

		// Movement Code
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			pos.x += spd * dt;
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			pos.x -= spd * dt;
		}

		// Capping the player's position to the screen
		pos.x = MathUtils.clamp(pos.x, 0, Main.WIDTH - width);

		// Gravity
		if (pos.y > 0) {
			velY += GameState.GRAVITY * dt;
		} else {
			pos.y = 0;
			velY = 0;
		}

		// Jumping
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isKeyPressed(Keys.UP)) {
			if (pos.y == 0) {
				velY = jumpSpd * dt;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if (pos.y > 0) {
				velY -= (jumpSpd * 1.4f) * dt;
			}
		}

		// Shooting
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isKeyJustPressed(Keys.Z)) {
			gs.entities.add(new Bullet(gs, new Vector2(pos.x + width / 2 - 8, pos.y + height), 16, 16));
		}

		// Checking for collisions with enemies for hp purposes
		for (int i = 0; i < gs.entities.size; i++) {
			Entity en = gs.entities.get(i);
			if (!(en instanceof Enemy))
				continue;
			Enemy e = (Enemy) gs.entities.get(i);
			if (isColliding(e)) {
				hp--;
				gs.enemies.removeValue(e, false);
				gs.entities.removeValue(e, false);
				if (hp <= 0) {
					GameState.DIED = true;
				}
			}
		}

		// Implementing the velocity to the actual position of the player
		pos.y += velY;
	}

	@Override
	public void render(SpriteBatch sb) {
		anim.render(sb);
		sb.draw(Textures.gun1, pos.x + (width / 2) - 16, pos.y + height, 32, 64);

		Fonts.calibri.setColor(Color.BLACK);
		Fonts.calibri.draw(sb, "HP: " + hp + " / " + maxHp, 20, 30);
	}

	public void setSpeed(float spd) {
		this.spd = spd;
	}

	public float getSpeed() {
		return spd;
	}

}
