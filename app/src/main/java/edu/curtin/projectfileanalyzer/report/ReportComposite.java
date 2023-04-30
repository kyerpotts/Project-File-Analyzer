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
   * Allows the parent class of an object to set it's childrens parent field
   * when adding it to it's list of children
   *
   * @param parentDirectory the parent directory that the object is being added
   *                        to as a child
   */
  public void setParent(ReportDirectory parentDirectory);

  /**
   * Provides a contract method that allows the ReportComposite to recurse
   * through the tree structure and display the report
   *
   * @param reportType decides what is displayed to the use based on the user
   *                   input
   */
  public void writeReport(ReportType reportType);
}
