package me.joosua.maingine;

import me.joosua.maingine.utils.LoggerManager;
import me.joosua.maingine.utils.SystemInfo;
import org.lwjgl.Version;

public class Maingine {

  public static final String VERSION = "0.0.1";

  /**
   * <p>Main constructor of Maingine. The first part of
   * initialization is done here.</p>
   */
  public Maingine() {

    LoggerManager.prepareLogger();

    SystemInfo.printInfo();

  }

}
