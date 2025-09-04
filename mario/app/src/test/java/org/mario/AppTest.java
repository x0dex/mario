package org.mario;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private App app = new App();
    @Test void appInstance() { assertNotNull(app); }
}
