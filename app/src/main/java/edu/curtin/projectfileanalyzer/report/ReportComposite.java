package edu.curtin.projectfileanalyzer.report;

import edu.curtin.projectfileanalyzer.filecomposite.FileComposite;

/**
 * TODO: Write a description of this interface
 *
 * @author Kyer Potts
 */
public interface ReportComposite extends FileComposite {
  public void displayReport();

  public ReportComposite getParent();
}
