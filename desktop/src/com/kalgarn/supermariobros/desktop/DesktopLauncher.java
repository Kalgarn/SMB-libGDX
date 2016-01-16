package com.kalgarn.supermariobros.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kalgarn.supermariobros.SuperMarioBros;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1200;
		config.height = 624;
		config.title = "Super Mario Bros";
		//config.resizable = false;
		//config.fullscreen = true;
		new LwjglApplication(new SuperMarioBros(), config);
	}
}
