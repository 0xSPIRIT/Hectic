package com.thechief.hectic.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thechief.hectic.Fonts;
import com.thechief.hectic.Main;

public class MenuState extends State {

	private GlyphLayout titleG, playG;
	private CharSequence title, play;

	private float color = 0;
	private boolean cDown = false;
	
	@Override
	public void create() {
		setUp(Main.WIDTH, Main.HEIGHT);
		title = "HECTIC";
		titleG = new GlyphLayout(Fonts.calibriLarge, title);
		play = "ENTER";
		playG = new GlyphLayout(Fonts.calibri, play);
	}

	@Override
	public void update(float dt) {
		Gdx.gl.glClearColor(color, color, color, 1);
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			StateManager.setCurrentState(new GameState());
		}
		if (!cDown) {
			color += 0.01f;
		} else {
			color -= 0.01f;
		}
		
		if (color < 0) {
			cDown = false;
		}
		if (color > 1) {
			cDown = true;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();

		Fonts.calibriLarge.setColor(Color.WHITE);
		Fonts.calibriLarge.draw(sb, title, Main.WIDTH / 2 - titleG.width / 2, Main.HEIGHT - 50);
		
		Fonts.calibri.setColor(new Color(0.6f, 0.6f, 0.6f, 1));
		Fonts.calibri.draw(sb, play, Main.WIDTH / 2 - playG.width / 2, Main.HEIGHT / 2);
		
		sb.end();
	}

	@Override
	public void dispose() {

	}

}
