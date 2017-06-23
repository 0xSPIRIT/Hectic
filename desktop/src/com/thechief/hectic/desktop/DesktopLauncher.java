package com.thechief.hectic.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.thechief.hectic.Main;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Main.WIDTH;
		config.height = Main.HEIGHT;
		config.title = Main.TITLE;
		config.resizable = false;
		new LwjglApplication(new Main(), config);
	}
}
