package sandbox;

import java.io.FileNotFoundException;
import java.io.IOException;

import core.Jengine.components.OpenGlConfigOptions;
import core.Jengine.components.Window;

public class MainApp {

	private static Window myWindow;

	public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
//		GlobalConfig.setDebugMode(false);
	
		OpenGlConfigOptions openGlConfig = new OpenGlConfigOptions(true);
		(myWindow = Window.getInstance()).create("Jengine", openGlConfig);
		
		myWindow.run();
	}
}
