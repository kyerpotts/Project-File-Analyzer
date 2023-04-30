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
  private ReportComposite parent;
  private List<Line> lines;
  private int depth;

  /**
   * Constructor for ReportFile object
   *
   * @param name   the name of the file. This also represents the value of the
   *               ReportComposite object
   * @param parent the ReportNode object that this object resides within
   */
  public ReportFile(String name, ReportComposite parent, int depth) {
    this.name = name;
    this.parent = parent;
    this.lines = new ArrayList<>();
    this.depth = depth;
    LOGGER.info(() -> name + " successfully instantiated.");
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void updateParentSize(int nodeSize) {
    // The parents of this object must be updated recursively in order to
    // calculate their total enclosing size
    parent.updateParentSize(lines.size());
  }

  @Override
  public int getSize() {
    return lines.size();
  }

  @Override
  public int getDepth() {
    return this.depth; // Get the current depth of the ReportFile object
  }

  public List<Line> getLines() {
    return this.lines;
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
