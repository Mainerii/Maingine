package maingine;

import org.junit.Test;
import static org.junit.Assert.*;

public class MaingineTest {

    @Test public void testMaingineInitialization() {

        assertEquals("Hello, World!", new Maingine().getGreeting());

    }

}
