package core.Jengine.components;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import core.Jengine.nodes.Node;
import core.utils.Logger;

public class Window extends Node {

	private static Window windowInstance = null;
	private long windowId;
	private GLFWVidMode mode;
	private long monitor;
	
	public Renderer renderer;
	
	private Window(String name) {
		super(name);
		this.renderer = new Renderer();
	}
		
	public static Window getWindow(String name) {

		if (windowInstance == null) {
			windowInstance = new Window(name);

			Logger.debugLog("new Window instance created");
			windowInstance.init();
			
			return windowInstance;
		}
		return windowInstance;
	}

	public void run() {
		loop();

		glfwSetErrorCallback(null).free();
		glfwTerminate();

	}

	private void init() {

		Logger.debugLog("Starting window...");
		// Set Error print
		GLFWErrorCallback.createPrint(System.err).set();

		if (! glfwInit()) {
			throw new IllegalStateException("Cannot initialized GLFW");
		}

		monitor = glfwGetPrimaryMonitor();
		mode = glfwGetVideoMode(this.monitor);

		glfwWindowHint(GLFW_RED_BITS, mode.redBits());
		glfwWindowHint(GLFW_GREEN_BITS, mode.greenBits());
		glfwWindowHint(GLFW_BLUE_BITS, mode.blueBits());
		glfwWindowHint(GLFW_REFRESH_RATE, mode.refreshRate());

		windowId = glfwCreateWindow(mode.width(), mode.height(), getName(), monitor, NULL);

		if (windowId == NULL) {
			throw new RuntimeException("Error on create GLFW window");
		}
		//setup key callback
		setKeyCallBacks(new KeyCallBacks());

		glfwMakeContextCurrent(windowId);// make opengl current context
		GL.createCapabilities();
		glfwSwapInterval(1);// enable v-sync
		glfwShowWindow(windowId);//  show window
		Logger.debugLog("Window Started: " + getName());
	}


	/**
	 * Set key back
	 * @param callBackFn Class which implements GLFWKeyCallbackI with all call back declaration
	 */
	private void setKeyCallBacks(KeyCallBacks callBackFn) {
		glfwSetKeyCallback(windowId, callBackFn);
	}


	private void loop() {
		renderer.render(windowId);
	}
}