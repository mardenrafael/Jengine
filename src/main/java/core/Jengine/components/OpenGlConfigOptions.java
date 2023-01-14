package core.Jengine.components;

public final class OpenGlConfigOptions {
	
	private final boolean vSync;
	
	public OpenGlConfigOptions(boolean vSync) {
		this.vSync = vSync;
	}

	public boolean vSyncEnable() {
		return vSync;
	}	
}