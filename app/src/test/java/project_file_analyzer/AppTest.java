/*
 * Tests the main entry point into the app
 */
package project_file_analyzer;

// import static org.junit.Assert.*;

import edu.curtin.projectfileanalyzer.App;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
    App classUnderTest;

    @Before
    public void setup() {
        classUnderTest = new App();
    }

    @Test
    public void appGetsDefaultFilePath() {
    }

    @Test
    public void greetIsValid() {
    }
}
