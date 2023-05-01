package edu.curtin.projectfileanalyzer.directoryvalidator;

import java.io.File;
import java.util.logging.Logger;

/**
 * Validates directories entered by the user. Valid and default directories are
 * returned depending on the outcome
 *
 * @author Kyer Potts
 */
public class DirectoryValidator {
  private static final Logger LOGGER = Logger.getLogger(DirectoryValidator.class.getName());

  /**
   * Checks to see whether the default path is required.
   *
   * @param path The path supplied to the program from the user when the program
   *             is executed.
   */
  public String determinePath(String[] path) throws DirectoryPathException {
    // There should only ever be 0 or 1 arguments supplied by the user
    if (path.length > 1) {
      LOGGER.severe(() -> "User supplied " + path.length +
          " arguments when program was executed.");

      throw new DirectoryPathException(
          "The CLI only accepts a single argument as a path");
    }

    String usablePath = "";

    if (path.length < 1) { // Use default path
      usablePath = System.getProperty("user.dir");
    } else if (path.length == 1) { // Use first argument as path
      usablePath = path[0];
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
    if (!root.exists()) { // Path must point to a file/folder
      LOGGER.warning(
          () -> root.getPath() + " Supplied path does not point to a file");
      return false;
    }

    if (root.isDirectory()) { // Path must point to a directory
      LOGGER.info(
          () -> root.getPath() + " Supplied path points to a directory");
      return true;
    }

    LOGGER.warning(
        () -> root.getPath() + " Supplied path does not point to a directory");
    return false;
  }
}
