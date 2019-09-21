package me.joosua.maingine.utils;

import me.joosua.maingine.Maingine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Version;

/**
 * <p>SystemInfo provides information about the system in simplified form.</p>
 *
 * @since unreleased
 */
public class SystemInfo {

  private static final Logger logger = LogManager.getLogger(SystemInfo.class);

  /**
   * <p>Print information about Maingine and about the operating system used.</p>
   *
   * @since 0.0.1
   */
  public static void printInfo() {

    logger.info("Maingine version: " + Maingine.VERSION);
    logger.info("Maingine debug mode: " + Maingine.DEBUG);
    logger.info("OS name: " + System.getProperty("os.name"));
    logger.info("OS version: " + System.getProperty("os.version"));
    logger.info("LWJGL version: " + Version.getVersion());

  }

}
