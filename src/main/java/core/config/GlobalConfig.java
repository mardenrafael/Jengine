package core.config;

import core.Jengine.nodes.Node;

public class GlobalConfig extends Node {

	private static boolean isDebug = true;
	
	public GlobalConfig(boolean isDebug) {
		super(null);
	}
	
	public static boolean isDebugMode() {
		return isDebug;
	}

	public static void setDebugMode(boolean isDebug) {
		GlobalConfig.isDebug = isDebug;
	}
}
