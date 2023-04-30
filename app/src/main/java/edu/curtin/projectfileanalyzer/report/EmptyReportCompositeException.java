package edu.curtin.projectfileanalyzer.report;

/**
 * This exception is triggered when an empty Report object is added to the
 * Report tree
 *
 * @author Kyer Potts
 */
public class EmptyReportCompositeException extends RuntimeException {
  public EmptyReportCompositeException(String message) {
    super(message);
  }
}
