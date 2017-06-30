package com.thechief.hectic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Fonts {

	public static BitmapFont calibri;
	public static BitmapFont calibriLarge;
	
	public static final void INIT_FONTS() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Calibri.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 24;
		calibri = generator.generateFont(parameter);
		generator.dispose();
		
		FreeTypeFontParameter parameter1 = new FreeTypeFontParameter(); 
		parameter1.size = 48;
		FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("Calibri.ttf"));
		calibriLarge = generator1.generateFont(parameter1);
	}

	public static final void DISPOSE_FONTS() {
		calibri.dispose();
		calibriLarge.dispose();
	}
}
