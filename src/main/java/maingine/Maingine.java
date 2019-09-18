package maingine;

import org.lwjgl.Version;

public class Maingine {

  private static final String VERSION = "0.0.1";

  /**
   * <p>Print information about Maingine and about the operating system used.</p>
   * @since 0.0.1
   */
  public static void printInfo() {

    System.out.println("Maingine version: " + VERSION);
    System.out.println("OS name: " + System.getProperty("os.name"));
    System.out.println("OS version: " + System.getProperty("os.version"));
    System.out.println("LWJGL version: " + Version.getVersion());

  }

}
