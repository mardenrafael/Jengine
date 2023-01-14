package core.utils;

import core.config.GlobalConfig;

public final class Logger {
	
	/**
	 * Just log on console
	 * @param log String Information which must to log on console
	 */
	public static void debugLog(Object log) {
		
		if (!GlobalConfig.isDebugMode()) {
			return;
		}
		
		System.out.println(log);
	}
	
	public static void logError(Object log, boolean isError) {	
				
		if (isError) {
			System.err.println(log);
			return;
		}
		
		debugLog(log);
	}
}
