/*
 * Tests the ParserDirectory class
 */
package projectfileanalyzer.directoryparser;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.curtin.projectfileanalyzer.DirectoryPathException;
import edu.curtin.projectfileanalyzer.directoryparser.FileParserComposite;
import edu.curtin.projectfileanalyzer.directoryparser.ParserDirectory;
import java.io.File;
import org.junit.Before;
import org.junit.Test;

/*
 * Tests the ParserDirectory class
 *
 */
public class ParserDirectoryTest {
  private ParserDirectory classUnderTest;
  private FileParserComposite child;

  @Before
  public void setup() {
    classUnderTest = new ParserDirectory("/TestPath");
    child = new ParserDirectory("/Child");
  }

  /**
   * Test behavious that ParserDirectory returns unqualified path
   *
   * @result The name should be the same as what the class was instantiated with
   */
  @Test
  public void testGetNameReturnsUnqualifiedPath() {
    assertEquals("Name should be '/TestPath'", classUnderTest.getName(),
                 "/TestPath");
  }

  /**
   * Test behaviour that ParserDirectory returns appropriately
   *
   * @result The default path should be the directory that the program is
   *         executed within
   */
  @Test
  public void testParserDirectoryIsDirectory() {
    assertTrue("ParserDirectory is a directory", classUnderTest.isDirectory());
  }

  /**
   * Test behaviour that when a child is added it is inserted into the children
   * list
   *
   * @result The child is added to the list
   */
  @Test
  public void testAddChildToChildrenList() {
    // TODO: Complete this test using reflection to verify that the child is
    // added to the list
  }
}
