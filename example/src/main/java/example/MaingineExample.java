package example;

import me.joosua.maingine.Maingine;
import me.joosua.maingine.settings.WindowSettings;

public class MaingineExample {

	public static void main(String[] args) {

	  Maingine.DEBUG = false;

    WindowSettings windowSettings = new WindowSettings();
    windowSettings.setTitle("Maingine example");
    windowSettings.setVisibility(true);

		new Maingine(windowSettings);

	}

}
