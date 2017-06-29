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

		// checking for enemy collisions
		for (int i = 0; i < gs.enemies.size; i++) {
			Enemy e = gs.enemies.get(i);
			if (isColliding(e)) {
				hp--;
				gs.enemies.removeValue(e, false);
				gs.entities.removeValue(e, false);
				if (hp <= 0) {
					GameState.DIED = true;
				}
			}
		}

		for (int i = 0; i < gs.meteors.size; i++) {
			Meteorite m = gs.meteors.get(i);
			if (isColliding(m)) {
				hp = 0;
				GameState.DIED = true;
			}
		}

		// Implementing the velocity to the actual position of the player
		pos.y += velY;
	}

	@Override
	public void render(SpriteBatch sb) {
		anim.render(sb);

		Fonts.calibri.setColor(Color.BLACK);
		Fonts.calibri.draw(sb, "HP: " + hp + " / " + maxHp, 20, 30);
	}

	public void setSpeed(float spd) {
		this.spd = spd;
	}

	public float getSpeed() {
		return spd;
	}

	public float getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(float maxHp) {
		this.maxHp = maxHp;
	}

	public float getHp() {
		return hp;
	}

	public void setHp(float hp) {
		this.hp = hp;
		this.hp = MathUtils.clamp(this.hp, 0, maxHp);
	}

}
