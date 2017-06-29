package com.thechief.hectic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	protected Vector2 pos;
	protected int width, height;
	protected Texture texture;
	
	public Entity(Texture texture, Vector2 pos) {
		this.texture = texture;
		this.pos = pos;
		this.width = texture.getWidth();
		this.height = texture.getHeight();
	}
	
	public Entity(Texture texture, Vector2 pos, int width, int height) {
		this(texture, pos);
		this.width = width;
		this.height = height;
	}
	
	public abstract void update(float dt);
	
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y, width, height);
	}
	
	float amp = -99999;
	
	public void vibrate(float min, float max) {
		vibrateX(min, max);
		vibrateY(min, max);
	}
	
	public void vibrateX(float min, float max) {
		pos.x += MathUtils.random(min, max);
	}

	public void vibrateY(float min, float max) {
		pos.y += MathUtils.random(min, max);
	}
	
	// GETTERS AND SETTERS:

	public double dist(Vector2 object1, Vector2 object2){
        return Math.sqrt(Math.pow((object2.x - object1.x), 2) + Math.pow((object2.y - object1.y), 2));
    }
	
	public Rectangle getBounds() {
		return new Rectangle(pos.x, pos.y, width, height);
	}
	
	public boolean isColliding(Entity e) {
		return getBounds().overlaps(e.getBounds());
	}
	
	public Vector2 getPos() {
		return pos;
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
}
