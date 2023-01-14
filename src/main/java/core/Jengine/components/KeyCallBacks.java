package core.Jengine.components;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.*;

import core.Jengine.nodes.Node;
import core.utils.Logger;

/**
 * Is there where all key callbacks are set
 */
public class KeyCallBacks extends Node implements GLFWKeyCallbackI {

	public KeyCallBacks() {
		super(null);
	}

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {

		if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
			Logger.debugLog("Closing window: " + window + "...");
			glfwSetWindowShouldClose(window, true);
		}
	}

}
