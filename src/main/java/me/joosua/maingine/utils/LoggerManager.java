package me.joosua.maingine.utils;

import java.io.File;

public class LoggerManager {

  /**
   * <p>Sets default system properties needed for logger.</p>
   *
   * @see #prepareLogger(boolean, String[])
   */
  public static void prepareLogger() {

    if (System.getProperty("maingine.logger.dir") == null) {
      prepareLogger(false, new String[] {"maingine"});
    }

  }

  /**
   * <p>Sets custom system properties needed for logger.</p>
   *
   * @param folders Folders for {@link #getLogDir(String[])}
   * @see #prepareLogger()
   */
  public static void prepareLogger(boolean debug, String[] folders) {

    System.setProperty("log4j2.debug", "" + debug);
    System.setProperty("maingine.logger.dir", getLogDir(folders));

  }

  /**
   * <p>Gets full path for logs to be made in system's temp dir.</p>
   * <p>Eg. on most Linux based systems <code>getLogDir(new String[] {"maingine", "logs"})</code>
   * will result to <i>/tmp/maingine/logs</i>.</p>
   *
   * @param folders Array with each folder for log dir
   * @return Absolute path to log dir
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
