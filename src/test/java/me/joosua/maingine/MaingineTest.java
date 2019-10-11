package me.joosua.maingine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import me.joosua.maingine.glfw.GlfwManager;
import me.joosua.maingine.glfw.window.Window;
import me.joosua.maingine.settings.WindowSettings;
import me.joosua.maingine.utils.LoggerManager;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MaingineTest {

  @Test
  @Order(1)
  public void testLogger() {

    LoggerManager.prepareLogger(false, new String[] {"maingine", "tests"});

    assertNotNull(System.getProperty("log4j2.debug"));
    assertNotNull(System.getProperty("maingine.logger.dir"));

    LogManager.getLogger(MaingineTest.class);

  }

  @Test
  @Order(2)
  public void testGlfw() {

    assertTrue(GlfwManager.init());

  }

  @Test
  @Order(3)
  public void testWindow() {

    Window window = new Window(null);

    assertEquals(0, window.getWindowID());
    assertFalse(window.isOpen());
    assertTrue(window.isCloseRequested());
    assertFalse(window.destroy());
    assertNull(window.getTitle());
    assertFalse(window.setTitle(""));
    assertFalse(window.setVisibility(true));
    assertFalse(window.isVisible());
    assertFalse(window.isResizable());
    assertFalse(window.destroy());
    assertEquals(0, window.getWindowID(), 0);

    WindowSettings settings = new WindowSettings();

    assertEquals("", settings.getTitle());
    settings.setTitle(null);
    assertEquals("", settings.getTitle());
    settings.setTitle("Example");
    assertEquals("Example", settings.getTitle());
    assertFalse(settings.isVisible());
    settings.setVisibility(true);
    assertTrue(settings.isVisible());
    assertFalse(settings.isResizable());
    settings.setResizable(true);
    assertTrue(settings.isResizable());

    window = new Window(settings);

    assertNotEquals(0, window.getWindowID());
    assertTrue(window.isOpen());
    assertFalse(window.isCloseRequested());
    assertEquals("Example", window.getTitle());
    assertTrue(window.setTitle(""));
    assertEquals("", window.getTitle());
    assertTrue(window.isVisible());
    assertTrue(window.setVisibility(false));
    assertFalse(window.isVisible());
    assertTrue(window.isResizable());
    assertTrue(window.destroy());
    assertFalse(window.setTitle(""));
    assertFalse(window.setVisibility(true));
    assertFalse(window.isVisible());
    assertFalse(window.isResizable());
    assertFalse(window.destroy());
    assertEquals(0, window.getWindowID());

  }

}
