package edu.curtin.projectfileanalyzer;

/**
 * This exception should be thrown when there is a problem with the arguments
 * supplied by the user when the program is executed.
 *
 * @author Kyer Potts
 */
public class DirectoryPathException extends Exception {
  public DirectoryPathException(String message) {
    super(message);
  }
}
