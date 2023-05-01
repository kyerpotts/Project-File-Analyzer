package edu.curtin.projectfileanalyzer.report;

import java.util.ArrayList;
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
  private ReportDirectory parent;
  private List<ReportComposite> children;
  private int size;

  /**
   * Constructor for ReportDirectory object
   *
   * @param name   the name of the directory. This also represents the value of
   *               the ReportComposite object
   * @param parent the object that this object resides within the Report tree
   */
  public ReportDirectory(String name) {
    this.name = name;
    this.size = 0;
    this.parent = null;
    children = new ArrayList<>();
    LOGGER.info(() -> name + " successfully instantiated.");
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getSize() {
    return this.size;
  }

  @Override
  public int getDepth() {
    // If the parent is null, it must be the root, therefor its depth is 0
    if (parent == null) {
      return 0;
    } else {
      // Get the current depth of the ReportDirectory object
      return parent.getDepth() + 1;
    }
  }

  @Override
  public void setParent(ReportDirectory parentDirectory) {
    // Reporting behaviour will be compromised if an empty ReportDirectory
    // object is added to a ReportTree
    if (this.size < 1) {
      throw new EmptyReportCompositeException(
          "Empty ReportFile object can not be added to a Report tree structure");
    }
    this.parent = parentDirectory;
    if (parent != null) { // null parent indicates that ReportDirectory is root
      this.parent.updateParentSize(this.size);
    }
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
    // The parent of the child must be set only when the child has been added to
    // a directory.
    child.setParent(this); //
    LOGGER.info(() -> child.getName() + " successfully added as a child");
  }

  /**
   * Allows the children of this class to update it's size (number of lines
   * contained within) whenever this object is set as the parent of the child
   *
   * @param nodeSize
   */
  public void updateParentSize(int nodeSize) {
    this.size += nodeSize; // Update this directory with the linecount of it's child
    // If the parent is the root, end recurse
  }
}
