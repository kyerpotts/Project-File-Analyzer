package edu.curtin.projectfileanalyzer.directoryvalidator;

import edu.curtin.projectfileanalyzer.DirectoryPathException;
import java.io.File;

/**
 * Validates directories entered by the user. Valid and default directories are
 * returned depending on the outcome
 *
 * @author Kyer Potts
 */
public class DirectoryValidator {

  /**
   * Checks to see whether the default path is required.
   *
   * @param path The path supplied to the program from the user when the program
   *             is executed.
   */
  public String determinePath(String[] path) throws DirectoryPathException {
    if (path.length > 1) {
      throw new DirectoryPathException(
          "The CLI only accepts a single argument as a path");
    }
    String usablePath = "";
    if (path.length < 1) { // Use default path
      usablePath = System.getProperty("user.dir");
    } else if (path.length == 1) {
      usablePath = path[0]; // Use first argument as path
    }
    return usablePath;
  }

  /**
   * The path must be validated before it can be used to build the Parser tree.
   * This method checks to see whether the supplied path points to a valid
   * directory.
   *
   */
  public boolean isValidPath(File root) {
    if (!root.exists()) {
      return false;
    }

    if (root.isDirectory()) {
      return true;
    }

    return false;
  }
}
