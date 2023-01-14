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
	
	public Renderer2D renderer;
	
	private Window() {
		super(null);
		this.renderer = new Renderer2D();
	}
		
	/**
	 * Create one window instance if not exists, if already exists just return the current instance
	 * @param name Title of Window
	 * @return Window Instance
	 */
	public static Window getInstance() {

		if (windowInstance == null) {
			windowInstance = new Window();

			Logger.debugLog("new Window instance created");
			
			return windowInstance;
		}
		return windowInstance;
	}

	
	public void run() {
		loop();

		glfwSetErrorCallback(null).free();
		glfwTerminate();
		glfwDestroyWindow(windowId);

	}

	public void create(String title, OpenGlConfigOptions OpenGlconfig) {
		
		setName(title);
		
		this.boot();
		
		Logger.debugLog("Booting window...");
		
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
		
		this.enableOpenGL(OpenGlconfig);
		
		glfwShowWindow(windowId);//  show window
		Logger.debugLog("Window booted successfully: \n\t" + getName());

	}
	
	private void enableOpenGL(OpenGlConfigOptions config) {
		
		Logger.debugLog("Set configs for Opengl \n" + config.toString());
		
		glfwMakeContextCurrent(windowId);// make opengl current context
		GL.createCapabilities();
		
		if (config.vSyncEnable()) {			
			glfwSwapInterval(1);// enable v-sync
		}
	}
	
	private void boot() {

		Logger.debugLog("Starting glfw...");
		// Set Error print
		GLFWErrorCallback.createPrint(System.err).set();

		if (! glfwInit()) {
			throw new IllegalStateException("Cannot initialized GLFW");
		}
		Logger.debugLog("Done!");
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