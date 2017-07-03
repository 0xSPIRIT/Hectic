package com.thechief.hectic.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.thechief.hectic.Fonts;
import com.thechief.hectic.Main;

public class ShopState extends State {

	private String shopText = "Black Market";

	// This will center the text on the x axis.
	private final float oShopX = Main.WIDTH / 2 - new GlyphLayout(Fonts.calibri, shopText).width / 2,
						oShopY = Main.HEIGHT - 35;
	private float shopX, shopY;
	
	private int time = 0;
	
	public ShopState(OrthographicCamera camera) {
		this.camera = camera;
	}
	
	@Override
	public void create() {
		shopX = oShopX;
		shopY = oShopY;
		Gdx.gl.glClearColor(0, 0, 0, 1);
		// TODO: Maybe fade in some music or something here into calming black market music. (lel)
	}

	@Override
	public void update(float dt) {
		time++;
		if (time % 10 == 0) {
			shopX = oShopX + MathUtils.random(-3, 3);
			shopY = oShopY + MathUtils.random(-3, 3);
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		
		Fonts.calibri.setColor(0.65f, 0.65f, 0.65f, 1);
		Fonts.calibri.draw(sb, shopText, shopX, shopY);
		
		Fonts.calibri.setColor(1f, 1f, 1f, 1f);
		Fonts.calibri.draw(sb, shopText, shopX - 2, shopY + 2);

		sb.end();
	}

	@Override
	public void dispose() {
		
	}

}
