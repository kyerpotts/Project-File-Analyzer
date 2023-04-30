package edu.curtin.projectfileanalyzer.report;

import edu.curtin.projectfileanalyzer.filecomposite.FileComposite;

/**
 * This interface extends the FileComposite interface, allowing Files to be
 * composite types instead of leaves
 *
 * @author Kyer Potts
 */
public interface ReportComposite extends FileComposite {

  /**
   * This method allows all instances of a ReportComposite to give an integer
   * value representation of it's size (the number of matched lines contained
   * within)
   *
   * @return The size of the composite object, or it's relative location to the
   *         real directory
   */
  public int getSize();

  /**
   * This contract allows various implementations to report their depth within a
   * Report tree
   *
   * @return the current depth of the object in the context of the
   *         ReportComposite tree structure
   */
  public int getDepth();

  /**
   * Provides a contract for ReportComposite objects to recursively update their
   * parent node size when their contents changes
   *
   * @param nodeSize is the current size of the ReportComposite leaf. This will
   *                 allow parents to recursively update their own size
   */
  public void updateParentSize(int nodeSize);

  /**
   * Provides a contract method that allows the ReportComposite to recurse
   * through the tree structure and display the report
   *
   * @param reportType decides what is displayed to the use based on the user
   *                   input
   */
  public void writeReport(ReportType reportType);
}
