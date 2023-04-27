package edu.curtin.projectfileanalyzer.directoryparser;

import edu.curtin.projectfileanalyzer.filecomposite.FileComposite;
import edu.curtin.projectfileanalyzer.report.ReportComposite;

/**
 * TODO: Write a description of this interface
 *
 * @author Kyer Potts
 */
public interface FileParserComposite extends FileComposite {
  public void parse(ReportComposite report);
}
