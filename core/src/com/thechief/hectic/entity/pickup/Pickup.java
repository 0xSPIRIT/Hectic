package com.thechief.hectic.entity.pickup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.entities.Entity;

public abstract class Pickup extends Entity {

	public Pickup(Texture texture, Vector2 pos, int width, int height) {
		super(texture, pos, width, height);
	}

	public Pickup(Texture texture, Vector2 pos) {
		super(texture, pos);
	}

	private int time = 0;

	public void bob(float spd, float height) {
		time++;
		pos.y += Math.sin(time * spd) * height;
	}

}
