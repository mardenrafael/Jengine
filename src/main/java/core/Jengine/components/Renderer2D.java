package core.Jengine.components;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.util.ArrayList;
import java.util.List;

import core.Jengine.nodes2D.Shape2D;

public class Renderer2D {

	private final List<Shape2D> itensToDraw = new ArrayList<Shape2D>();

	public void add(Shape2D obj) {
		this.itensToDraw.add(obj);
	}

	public void render(long window) {
		while(!glfwWindowShouldClose(window)) {

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glClearColor(0.0f, 0.0f, 0.4f, 0.0f);

			for(Shape2D item : itensToDraw) {
				item.draw();
			}

			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}
}
