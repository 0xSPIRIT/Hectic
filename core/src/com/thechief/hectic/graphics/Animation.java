package com.thechief.hectic.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thechief.hectic.entities.Entity;

public class Animation {

	private long time = 0;
	public int index = 0;
	private int interval;
	private int width, height;
	private Texture[] textures;
	private Entity parent;
	
	public Animation(Entity parent, Texture[] textures, int interval, int width, int height) {
		this.parent = parent;
		this.textures = textures;
		this.interval = interval;
		this.width = width;
		this.height = height;
	}
	
	public Animation(Entity parent, Texture[] textures, int interval) {
		this(parent, textures, interval, parent.getWidth(), parent.getHeight());
	}
	
	public void update() {
		time++;
		if (time % interval == 0) {
			if (index < textures.length - 1) {
				index++;
			} else {
				index = 0;
			}
		}
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(textures[index], parent.getPos().x, parent.getPos().y, width, height);
	}
	
	public Texture[] getTextures() {
		return textures;
	}
	
}
