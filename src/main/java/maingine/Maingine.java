package maingine;

import org.lwjgl.*;

public class Maingine {

	private static final String VERSION = "unreleased";

	public static void printInfo() {

		System.out.println("Maingine version: " + VERSION);
		System.out.println("OS name: " + System.getProperty("os.name"));
		System.out.println("OS version: " + System.getProperty("os.version"));
		System.out.println("LWJGL version: " + Version.getVersion());

	}

}
