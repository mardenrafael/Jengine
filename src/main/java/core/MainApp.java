package core;

import java.io.FileNotFoundException;
import java.io.IOException;

import core.Jengine.components.ShaderLoader;
import core.Jengine.components.Window;
import core.Jengine.nodes.Triangle;
import core.config.GlobalConfig;

public class MainApp {

	private static Window myWindow;

	public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
		GlobalConfig.setDebugMode(false);

		myWindow = Window.getWindow("Jengine");

		ShaderLoader triSl = new ShaderLoader(
				System.getProperty("user.dir") + "/src/main/shaders/vertex.glsl", 
				System.getProperty("user.dir") + "/src/main/shaders/fragment.glsl");

		float[] triVertices = {
				-0.2f, -0.05f, 0f,
				0.05f, -0.05f, 0f,
				-0.2f, 0.3f, 0f
		};
		Triangle myTri = new Triangle(triVertices, triSl, "Center 1"); //centro parte 1

		float[] triVertices2 = {
				-0.2f, 0.3f, 0f,
				0.05f, -0.05f, 0f,
				0.05f, 0.3f, 0f
		};
		Triangle myTri2 = new Triangle(triVertices2, triSl, "Center 2"); //centro parte 2

		float[] triVertices3 = {
				-0.45f, 0.2f, 0f,
				-0.2f, -0.05f, 0f,
				-0.2f, 0.3f, 0f
		};
		Triangle myTri3 = new Triangle(triVertices3, triSl, "Left side");// ponta esquerda

		float[] triVertices4 = {
				-0.2f, 0.3f, 0f,
				0.05f, 0.3f, 0f,
				-0.05f, 0.75f, 0f
		};
		Triangle myTri4 = new Triangle(triVertices4, triSl, "Top Side"); // Ponta de cima

		float[] triVertices5 = {
				0.35f, 0.2f, 0f,
				0.05f, -0.05f, 0f,
				0.05f, 0.3f, 0f
		};
		Triangle myTri5 = new Triangle(triVertices5, triSl, "Right side");// ponta direita

		float[] triVertices6 = {
				0f, -0.5f, 0f,
				0.05f, -0.05f, 0f,
				-0.09f, -0.05f, 0f,
		};
		Triangle myTri6 = new Triangle(triVertices6, triSl, "Right leg"); // Perna direita

		float[] triVertices7 = {
				-0.15f, -0.5f, 0f,
				-0.2f, -0.05f, 0f,
				-0.09f, -0.05f, 0f,
		};
		Triangle myTri7 = new Triangle(triVertices7, triSl, "Left leg"); // Perna direita


		myWindow.renderer.add(myTri);
		myWindow.renderer.add(myTri2);
		myWindow.renderer.add(myTri3);
		myWindow.renderer.add(myTri4);
		myWindow.renderer.add(myTri5);
		myWindow.renderer.add(myTri6);
		myWindow.renderer.add(myTri7);


		myWindow.run();
	}
}
