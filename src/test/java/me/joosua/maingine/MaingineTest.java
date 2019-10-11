package me.joosua.maingine;

import me.joosua.maingine.glfw.GlfwManager;
import me.joosua.maingine.glfw.window.Window;
import me.joosua.maingine.settings.WindowSettings;
import me.joosua.maingine.utils.LoggerManager;
import org.junit.Assert;
import org.junit.Test;

public class MaingineTest {

  @Test public void testWindow() {

    LoggerManager.prepareLogger(false, new String[] {"maingine", "tests"});
    Assert.assertEquals(true, GlfwManager.init());

    Window window = new Window(null);

    Assert.assertEquals(0, window.getWindowID());
    Assert.assertEquals(false, window.isOpen());
    Assert.assertEquals(true, window.isCloseRequested());
    Assert.assertEquals(false, window.destroy());
    Assert.assertEquals(null, window.getTitle());
    Assert.assertEquals(false, window.setTitle(""));
    Assert.assertEquals(false, window.setVisibility(true));
    Assert.assertEquals(false, window.isVisible());
    Assert.assertEquals(false, window.isResizable());
    Assert.assertEquals(false, window.destroy());
    Assert.assertEquals(0, window.getWindowID(), 0);

    WindowSettings settings = new WindowSettings();

    Assert.assertEquals("", settings.getTitle());
    settings.setTitle(null);
    Assert.assertEquals("", settings.getTitle());
    settings.setTitle("Example");
    Assert.assertEquals("Example", settings.getTitle());
    Assert.assertEquals(false, settings.isVisible());
    settings.setVisibility(true);
    Assert.assertEquals(true, settings.isVisible());
    Assert.assertEquals(false, settings.isResizable());
    settings.setResizable(true);
    Assert.assertEquals(true, settings.isResizable());

    window = new Window(settings);

    Assert.assertNotEquals(0, window.getWindowID());
    Assert.assertEquals(true, window.isOpen());
    Assert.assertEquals(false, window.isCloseRequested());
    Assert.assertEquals("Example", window.getTitle());
    Assert.assertEquals(true, window.setTitle(""));
    Assert.assertEquals("", window.getTitle());
    Assert.assertEquals(true, window.isVisible());
    Assert.assertEquals(true, window.setVisibility(false));
    Assert.assertEquals(false, window.isVisible());
    Assert.assertEquals(true, window.isResizable());
    Assert.assertEquals(true, window.destroy());
    Assert.assertEquals(false, window.setTitle(""));
    Assert.assertEquals(false, window.setVisibility(true));
    Assert.assertEquals(false, window.isVisible());
    Assert.assertEquals(false, window.isResizable());
    Assert.assertEquals(false, window.destroy());
    Assert.assertEquals(0, window.getWindowID());

  }

}
