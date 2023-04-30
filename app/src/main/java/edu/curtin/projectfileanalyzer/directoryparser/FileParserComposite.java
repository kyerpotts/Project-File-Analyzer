package edu.curtin.projectfileanalyzer.directoryparser;

import edu.curtin.projectfileanalyzer.filecomposite.FileComposite;
import edu.curtin.projectfileanalyzer.matcher.CriteriaMatcher;
import edu.curtin.projectfileanalyzer.report.ReportComposite;

/**
 * Interface provides a contract for all FileParser composite types. A file
 * parser is a file tree intended to represent a file/directory tree structure
 * in memory, that can be parsed for the purposes of reporting
 *
 * @author Kyer Potts
 */
public interface FileParserComposite extends FileComposite {

  /**
   * Recurses over the FileParser tree structure in order to generate a
   * ReportComposite tree
   *
   * @param report The report object that will be used to generate a report
   *               based on user defined parameters
   */
  public ReportComposite parse(CriteriaMatcher criteriaMatcher);
}
