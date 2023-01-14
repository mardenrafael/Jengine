package sandbox; 

import java.io.FileNotFoundException;
import java.io.IOException;

import core.Jengine.components.OpenGlConfigOptions;
import core.Jengine.components.Window;
import core.Jengine.nodes.Triangle;
import core.Jengine.nodes2D.Shape2D;
import core.config.GlobalConfig;

public class MainApp {

	private static Window myWindow;

	public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
		GlobalConfig.setDebugMode(false);

		OpenGlConfigOptions openGlConfig = new OpenGlConfigOptions(true);
		(myWindow = Window.getInstance()).create("Jengine", openGlConfig);

		float[] vertices = {
				-1.0f, -1.0f, 0.0f,
				1.0f, -1.0f, 0.0f,
				0.0f, 1.0f, 0.0f,
		};
		Shape2D triangle = new Triangle(vertices, null, null);


		myWindow.renderer.add(triangle);

		myWindow.run();
	}
}
