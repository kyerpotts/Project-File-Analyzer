package edu.curtin.projectfileanalyzer.report;

/**
 * Provides a contract for reporting types
 *
 * @author Kyer Potts
 */
public interface ReportType {
  /**
   * This method takes in a ReportComposite object, and displays it to screen
   * depending on the type of report that has been selected by the user
   *
   * @param reportParent This object should be one of either a ReportDirectory
   *                     or
   *                     a ReportFile. The method will recurse through the
   *                     ReportComposite tree and display the contents of the
   *                     report based on the
   *                     criteria and report type provided by the user
   */
  public void writeComposite(ReportComposite reportParent);
}
