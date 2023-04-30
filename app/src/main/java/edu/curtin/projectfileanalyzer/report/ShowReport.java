package edu.curtin.projectfileanalyzer.report;

import edu.curtin.projectfileanalyzer.directoryparser.Line;
import java.util.logging.Logger;

/**
 * Writes a report variation that only displays the number of lines that have
 * been matched by the parser
 *
 * @author Kyer Potts
 */
public class ShowReport implements ReportType {
  private static final Logger LOGGER = Logger.getLogger(CountReport.class.getName());

  @Override
  public void reportOnDirectory(ReportDirectory reportDirectory) {
    assert reportDirectory.getSize() > 0; // The directory cannot be empty if being reported on
    writeComposite(reportDirectory); // Write the directory name
    LOGGER.info(() -> "Successfully reported " + reportDirectory.getName());
  }

  @Override
  public void reportOnFile(ReportFile reportFile) {
    assert reportFile.getSize() > 0; // The file cannot be empty if being reported on
    writeComposite(reportFile); // Write the file name
    writeFileContents(reportFile); // Write the lines in the file
    LOGGER.info(() -> "Successfully reported " + reportFile.getName());
  }

  /**
   * Writes a line for any ReportComposite object for the purposes of a line
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

  /**
   * Iterates through the contents of a reportFile object and displays the lines
   * contained within
   *
   * @param reportFile the object containing lines which need to be displayed
   */
  private void writeFileContents(ReportFile reportFile) {
    String tabDepth = "";
    for (int i = 0; i < reportFile.getDepth() + 1; i++) {
      tabDepth += "  ";
    }
    for (Line line : reportFile.getLines()) {
      System.out.println(tabDepth + line.getContent());
    }
  }
}
