package edu.curtin.projectfileanalyzer.report;

import edu.curtin.projectfileanalyzer.directoryparser.Line;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class represents a file node within a Report tree. It is not a composite
 * leaf as it encloses lines, which are considered leaves for the purpose of
 * reporting
 *
 * @author Kyer Potts
 */
public class ReportFile implements ReportComposite {
  private static final Logger LOGGER = Logger.getLogger(ReportFile.class.getName());
  private String name;
  private ReportDirectory parent;
  private List<Line> lines;

  /**
   * Constructor for ReportFile object
   *
   * @param name   the name of the file. This also represents the value of the
   *               ReportComposite object
   * @param parent the ReportNode object that this object resides within
   */
  public ReportFile(String name) {
    this.name = name;
    this.lines = new ArrayList<>();
    LOGGER.info(() -> name + " successfully instantiated.");
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getSize() {
    return lines.size();
  }

  @Override
  public int getDepth() {
    // Get the current depth of the ReportFile object
    return parent.getDepth() + 1;
  }

  public List<Line> getLines() {
    return this.lines;
  }

  @Override
  public void setParent(ReportDirectory parentDirectory) {
    // Reporting behaviour will be compromised if an empty ReportFile object is
    // added to a ReportTree
    if (this.lines.size() < 1) {
      throw new EmptyReportCompositeException(
          "Empty ReportFile object can not be added to a Report tree structure");
    }

    this.parent = parentDirectory;
    // The size of the parent directory needs to be updated as soon as this is
    // added to its children
    this.parent.updateParentSize(this.lines.size());
  }

  @Override
  public void writeReport(ReportType reportType) {
    LOGGER.info(() -> "Begin writing report on file: " + this.name);
    reportType.reportOnFile(this); // Write a report for the current node
  }

  /**
   * Add a matched line to the ReportFile object
   *
   * @param line a line which has been matched against user entered criteria
   */
  public void addLine(Line line) {
    // Children being added to a ReportFile must only be ReportLineWrapper
    // objects
    lines.add(line);
    LOGGER.info(
        () -> "Line: " + line.getNumber() + " added to file: " + this.name);
  }
}
