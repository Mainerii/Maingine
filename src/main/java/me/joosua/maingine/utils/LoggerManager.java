package me.joosua.maingine.utils;

import java.io.File;
import java.io.PrintStream;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.io.IoBuilder;
import org.lwjgl.system.Configuration;

/**
 * <p>Everything to do with Log4j logger.</p>
 *
 * @since unreleased
 */
public class LoggerManager {

  /**
   * <p>Sets default system properties and streams needed for logger.</p>
   *
   * @see #prepareLogger(boolean, String[])
   * @since unreleased
   */
  public static void prepareLogger() {

    if (System.getProperty("maingine.logger.dir") == null) {
      prepareLogger(false, new String[] {"maingine"});
    }

  }

  /**
   * <p>Sets custom system properties and streams needed for logger.</p>
   *
   * @param folders Folders for {@link #getLogDir(String[])}
   * @see #prepareLogger()
   * @since unreleased
   */
  public static void prepareLogger(boolean debug, String[] folders) {

    System.setProperty("log4j2.debug", "" + debug);
    System.setProperty("maingine.logger.dir", getLogDir(folders));

    PrintStream stream = IoBuilder.forLogger("LWJGL").setLevel(Level.DEBUG).buildPrintStream();
    Configuration.DEBUG_STREAM.set(stream);

    System.setErr(IoBuilder.forLogger("Maingine").setLevel(Level.ERROR).buildPrintStream());
    System.setOut(IoBuilder.forLogger("Maingine").setLevel(Level.DEBUG).buildPrintStream());

  }

  /**
   * <p>Gets full path for logs to be made in system's temp dir.</p>
   * <p>Eg. on most Linux based systems <code>getLogDir(new String[] {"maingine", "logs"})</code>
   * will result to <i>/tmp/maingine/logs</i>.</p>
   *
   * @param folders Array with each folder for log dir
   * @return Absolute path to log dir
   * @since unreleased
   */
  public static String getLogDir(String[] folders) {

    String path = System.getProperty("java.io.tmpdir");

    if (folders == null) return path;

    for (String folder : folders) {
      path += File.separator + folder;
    }

    return path;

  }

}
