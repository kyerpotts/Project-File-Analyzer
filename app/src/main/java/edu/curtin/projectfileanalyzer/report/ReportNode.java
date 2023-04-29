package edu.curtin.projectfileanalyzer.report;

/**
 * Interface for non-leaf classes that form a Report tree
 * 
 * @author Kyer Potts
 */
public interface ReportNode {
  /**
   * Provides a contract for ReportNodes to recursively update their parent node
   * size when their contents changes
   */
  public void updateParentSize();
}
