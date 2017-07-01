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
	
	// SpriteBatch stuff
	private SpriteBatch sb;
	private boolean collision = false;
	private int time = 0;
	
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
		// Animating the texture only if the game is not paused
		if (!GameState.PAUSED) {
			anim.update();
		}

		// Movement Code
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			GameState.PAUSED = false;
			pos.x += spd * dt;
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			GameState.PAUSED = false;
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
			GameState.PAUSED = false;
			if (pos.y == 0) {
				velY = jumpSpd * dt;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			GameState.PAUSED = false;
			if (pos.y > 0) {
				velY -= (jumpSpd * 1.4f) * dt;
			}
		}

		// checking for enemy collisions
		for (int i = 0; i < gs.entities.size; i++) {
			Entity e = gs.entities.get(i);
			if (e instanceof Enemy) {
				Enemy en = (Enemy) e;
				if (isColliding(en)) {
					hp--;
					if (sb != null) {
						collision = true;
					}
					gs.enemies.removeValue(en, false);
					gs.entities.removeValue(en, false);
					Explosion ex = new Explosion(gs, new Vector2(pos.x - 32, pos.y - 32), 128, 128);
					gs.entities.add(ex);
					if (hp <= 0) {
						GameState.DIED = true;
					}
				}
			}
		}
		
		int delayForCollisionRed = 20;
		
		if (collision) {
			if (time < delayForCollisionRed) {
				time++;
				sb.setColor(1, 0, 1, 1);
			} else {
				time = 0;
				collision = false;
				sb.setColor(1, 1, 1, 1);
			}
		}

		for (int i = 0; i < gs.meteors.size; i++) {
			Meteorite m = gs.meteors.get(i);
			if (isColliding(m)) {
				hp -= maxHp / 2;
				if (sb != null) {
					collision = true;
				}
				gs.meteors.removeValue(m, false);
				gs.entities.removeValue(m, false);
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
		if (this.sb == null) {
			this.sb = sb;
		}
		if (!collision) {
			sb.setColor(1, 1, 1, 1);
		} else {
			sb.setColor(1, 0, 1, 1);
		}
		anim.render(sb);
		sb.setColor(1, 1, 1, 1);

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
