package edu.curtin.projectfileanalyzer;

/**
 * This exception should be thrown if the user attempts to run a report without
 * first selecting appropriate options.
 *
 * @author Kyer Potts
 */
public class InvalidReportArgumentsException extends Exception {
  public InvalidReportArgumentsException(String message) {
    super(message);
  }
}
