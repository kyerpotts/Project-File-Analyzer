package edu.curtin.projectfileanalyzer.report;

import java.util.List;
import java.util.logging.Logger;

/**
 * This class represents a directory node within a Report tree.
 *
 * @author Kyer Potts
 */
public class ReportDirectory implements ReportComposite {
  private static final Logger LOGGER = Logger.getLogger(ReportDirectory.class.getName());
  private String name;
  private ReportComposite parent;
  private List<ReportComposite> children;
  private int size;
  private int depth;

  /**
   * Constructor for ReportDirectory object
   *
   * @param name   the name of the directory. This also represents the value of
   *               the ReportComposite object
   * @param parent the object that this object resides within the Report tree
   */
  public ReportDirectory(String name, ReportComposite parent, int depth) {
    this.name = name;
    this.parent = parent;
    this.size = 0;
    this.depth = depth;
    LOGGER.info(() -> name + " successfully instantiated.");
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void updateParentSize(int nodeSize) {
    this.size += nodeSize; // Update this directory with the linecount of it's child
    // If the parent is the root, end recurse
    if (parent != null) {
      parent.updateParentSize(nodeSize);
    }
  }

  @Override
  public int getSize() {
    return this.size;
  }

  @Override
  public int getDepth() {
    return this.depth; // Get the current depth of the ReportDirectory object
  }

  @Override
  public void writeReport(ReportType reportType) {
    LOGGER.info(() -> "Begin writing report on directory: " + this.name);
    reportType.reportOnDirectory(this); // Write a report for the current node
    for (ReportComposite node : children) {
      node.writeReport(reportType); // Recurse over children
    }
  }

  /**
   * Adds a child ReportComposite object to the Report tree
   *
   * @param child a child ReportComposite object to be added
   */
  public void addChild(ReportComposite child) {
    LOGGER.info(() -> "Adding child: " + child.getName());
    children.add(child);
    LOGGER.info(() -> child.getName() + " successfully added as a child");
  }
}
