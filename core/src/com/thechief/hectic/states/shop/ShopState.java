package com.thechief.hectic.states.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.hectic.Fonts;
import com.thechief.hectic.Main;
import com.thechief.hectic.entities.Entity;
import com.thechief.hectic.states.State;

public class ShopState extends State {
	
	private String shopText = "Black Market";

	// This will center the text on the x axis.
	private final float oShopX = Main.WIDTH / 2 - new GlyphLayout(Fonts.calibri, shopText).width / 2,
						oShopY = Main.HEIGHT - 35;
	private float shopX, shopY;
	
	private int time = 0;
	
	private Array<MenuItem> items = new Array<MenuItem>();
	
	private ShapeRenderer sr;
	
	public ShopState(OrthographicCamera camera) {
		this.camera = camera;
	}
	
	@Override
	public void create() {
		shopX = oShopX;
		shopY = oShopY;
		sr = new ShapeRenderer();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		// TODO: Maybe fade in some music or something here into calming black market music. (lel)
		items.add(new MenuItem(new Vector2(100, 100), 128, 96, 1000, "Drugs", "Gives you\nmore health"));
		items.add(new MenuItem(new Vector2(250, 100), 128, 96, 9999, "Druga1", "Kills you instantly"));
		items.add(new MenuItem(new Vector2(400, 100), 128, 96, 0, "Linus", "Linus from\nLinusTechTips.\n(does nothing)"));
	}

	@Override
	public void update(float dt) {
		time++;
		if (time % 10 == 0) {
			shopX = oShopX + MathUtils.random(-3, 3);
			shopY = oShopY + MathUtils.random(-3, 3);
		}
		for (MenuItem m : items) {
			m.update();
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		
		sr.setProjectionMatrix(camera.combined);
		sr.begin(ShapeType.Line);
		
		Fonts.calibri.setColor(0.65f, 0.65f, 0.65f, 1);
		Fonts.calibri.draw(sb, shopText, shopX, shopY);
		
		Fonts.calibri.setColor(1f, 1f, 1f, 1f);
		Fonts.calibri.draw(sb, shopText, shopX - 2, shopY + 2);

		for (MenuItem m : items) {
			m.render(sr);
			Fonts.calibri.draw(sb, m.name, m.pos.x + 2, m.pos.y + 2);
			Fonts.calibri.draw(sb, m.description, m.pos.x + 2, m.pos.y + 32);
			Fonts.calibri.draw(sb, "$" + m.cost + ".00", m.pos.x + m.width - 24, m.pos.y + 2);
		}		
		sr.end();
		sb.end();
	}

	@Override
	public void dispose() {
		
	}

}
