/*
 * Tests the file validator class
 */
package project_file_analyzer.directoryvalidator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.curtin.projectfileanalyzer.DirectoryPathException;
import edu.curtin.projectfileanalyzer.directoryvalidator.DirectoryValidator;
import java.io.File;
import org.junit.Before;
import org.junit.Test;

public class DirectoryValidatorTest {
  private DirectoryValidator classUnderTest;
  private File root;
  private String[] args;

  @Before
  public void setup() {
    classUnderTest = new DirectoryValidator();
    root = mock(File.class);
  }

  /**
   * Test behaviour that a default path is provided when one is not supplied by
   * the user
   *
   * @result The default path should be the directory that the program is
   *         executed within
   */
  @Test
  public void testGetDefaultPath() throws DirectoryPathException {
    // Empty string array mimics program being run without args
    args = new String[0];
    // user.dir returns the path of the directory the program is being run in
    assertEquals("Default Path is returned as working directory",
        System.getProperty("user.dir"),
        classUnderTest.determinePath(args));
  }

  /**
   * Test behaviour that if path is supplied by the user, it returned to move
   * onto validation
   *
   * @result The path supplied by the user is returned if the args passed into
   *         the program when executed from the CLI are valid
   */
  @Test
  public void testGetSuppliedPath() throws DirectoryPathException {
    // Array should mimic sprogram being run with a single arg
    args = new String[1];
    args[0] = "TestPath";
    assertEquals("Supplied path is returned as working directory", "TestPath",
        classUnderTest.determinePath(args));
  }

  /**
   * Test behaviour that exception should be thrown if the user supplies more
   * than one argument
   *
   * @result The user should not be supplying more than one argument. Doing so
   *         may result in unintended behaviour
   *
   */
  @Test(expected = DirectoryPathException.class)
  public void testExceptionThrownIfCLIArgsInvalid()
      throws DirectoryPathException {
    args = new String[2];
    classUnderTest.determinePath(args);
  }

  /**
   * Test behavior that a usable directory is valid for the program to continue
   *
   * @result When the path supplied by the user is valid it must be used
   */
  @Test
  public void testValidatesPathForValidDirectory() {
    // The File object has been constructed with a valid path
    when(root.exists()).thenReturn(true);
    // The path supplied to the File object points to a directory
    when(root.isDirectory()).thenReturn(true);

    assertTrue("Path to directory validates to true",
               classUnderTest.isValidPath(root));
  }

  /**
   * Test behaviour that invalid directories should not be used and the program
   * should not continue
   *
   * @result When the path supplied by the user points to a file instead of a
   *         directory, it should not be used to build a parser object
   */
  @Test
  public void testValidatesFalseForInvalidDirectory() {
    // The File object has been constructed with a valid path
    when(root.exists()).thenReturn(true);
    // The path supplied to the File object points to a directory
    when(root.isDirectory()).thenReturn(false);

    assertFalse("Path to file validates to false",
                classUnderTest.isValidPath(root));
  }

  /**
   * Test behaviour that non-existent files should not be used and the program
   * should not continue
   *
   * @result When the path supplied by the user points to neither a file or
   *         directory, it should not be used at all
   */
  @Test
  public void testValidatesFalseForNonExistentDirectory() {
    // The File object has been constructed with a valid path
    when(root.exists()).thenReturn(false);
    // The path supplied to the File object points to a directory
    when(root.isDirectory()).thenReturn(false);

    assertFalse("Non-Existent path validates to false",
                classUnderTest.isValidPath(root));
  }
}
