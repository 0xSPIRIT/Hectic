package com.thechief.hectic.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.hectic.Main;
import com.thechief.hectic.Textures;
import com.thechief.hectic.states.GameState;

public class Player extends Entity {

	private GameState gs;
	
	// Texture Animations
	private long lastTime;
	private Array<Texture> textures;
	private int index = 0;
	
	private float spd;
	
	// Jumping
	private float jumpSpd = 690;
	private float velY = 0;
	
	public Player(GameState gs, Vector2 pos, int width, int height) {
		super(Textures.player1, pos, width, height);
		this.gs = gs;
		lastTime = System.currentTimeMillis();
		textures = new Array<Texture>();
		textures.add(Textures.player1);
		textures.add(Textures.player2);
		
		spd = 400;
	}

	@Override
	public void update(float dt) {
		// Animating the texture
		animate();
		
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
		
		// Implementing the velocity to the actual position of the player
		pos.y += velY;
	}
	
	@Override
	public void render(SpriteBatch sb) {
		super.render(sb);
		sb.draw(Textures.gun1, pos.x + (width / 2) - 16, pos.y + height, 32, 64);
	}
	
	private void animate() {
		if (System.currentTimeMillis() - lastTime > 100) {
			if (index < textures.size - 1) {
				index++;
			} else if (index == textures.size - 1) {
				index = 0;
			}
			lastTime = System.currentTimeMillis();
		}
		
		texture = textures.get(index);
	}
	
}
