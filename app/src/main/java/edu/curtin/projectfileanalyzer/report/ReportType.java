package edu.curtin.projectfileanalyzer.report;

/**
 * Provides a contract for reporting types
 *
 * @author Kyer Potts
 */
public interface ReportType {
  /**
   * This method takes in a ReportDirectory object, and displays it to screen
   * depending on the type of report that has been selected by the user
   *
   * @param reportDirectory The ReportDirectory object that is currently being
   *                        displayed in the report
   */
  public void reportOnDirectory(ReportDirectory reportDirectory);

  /**
   * This method takes in a ReportFile object, and displays it to screen
   * depending on the type of report that has been selected by the user
   *
   * @param reportFile The ReportFile object that is currently being dispalyed
   *                   within the report
   */
  public void reportOnFile(ReportFile reportFile);
}
