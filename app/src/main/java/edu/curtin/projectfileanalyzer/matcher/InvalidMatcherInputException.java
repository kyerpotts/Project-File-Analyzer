package edu.curtin.projectfileanalyzer.matcher;

/**
 * This exception should be thrown when the user gives invalid input to the
 * matcher
 *
 * @author Kyer Potts
 */
public class InvalidMatcherInputException extends Exception {
  public InvalidMatcherInputException(String message) {
    super(message);
  }
}
