package edu.curtin.projectfileanalyzer.report;

import java.util.logging.Logger;

/**
 * Writes a report variation that only displays the number of lines that have
 * been matched by the parser
 *
 * @author Kyer Potts
 */
public class CountReport implements ReportType {
  private static final Logger LOGGER = Logger.getLogger(CountReport.class.getName());

  @Override
  public void reportOnDirectory(ReportDirectory reportDirectory) {
    assert reportDirectory.getSize() > 0; // The directory cannot be empty if being reported on
    writeComposite(reportDirectory);
    LOGGER.info(() -> "Successfully reported " + reportDirectory.getName());
  }

  @Override
  public void reportOnFile(ReportFile reportFile) {
    assert reportFile.getSize() > 0; // The file cannot be empty if being reported on
    writeComposite(reportFile);
    LOGGER.info(() -> "Successfully reported " + reportFile.getName());
  }

  /**
   * Writes a line for any ReportComposite object for the purposes of a count
   * report
   *
   * @param reportComposite
   */
  private void writeComposite(ReportComposite reportComposite) {
    // tabDepth is used to calculate the alignment of the name
    String tabDepth = "";
    for (int i = 0; i < reportComposite.getDepth(); i++) {
      tabDepth += "  "; // Alignment indents is two spaces for readability
    }
    // Print the name and number of lines contained within the ReportObject
    System.out.println(tabDepth + reportComposite.getName() + ": " +
        reportComposite.getSize() + " lines");
  }
}
