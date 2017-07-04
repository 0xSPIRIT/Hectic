package com.thechief.hectic.states.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.thechief.hectic.Fonts;

public class MenuItem {
	
	public Vector2 pos;
	public int width;
	public int height;
	public String name, description;
	public int cost;
	
	public MenuItem(Vector2 pos, int width, int height, int cost, String name, String description) {
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.cost = cost;
		this.name = name;
		this.description = description;
	}
	
	public void update() {
		int mx = Gdx.input.getX();
		int my = Gdx.input.getY();
		if (Gdx.input.isTouched()) {
			if (mx >= pos.x && mx <= pos.x + width) {
				if (my >= pos.y && my <= pos.y + height) {
					System.out.println("Pressed an item");
				}	
			}
		}
	}
	
	public void render(ShapeRenderer sr) {
		sr.rect(pos.x, pos.y, width, height);
	}
	
}