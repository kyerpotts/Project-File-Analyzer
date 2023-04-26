package edu.curtin.projectfileanalyzer.directoryparser;

import edu.curtin.projectfileanalyzer.report.ReportComposite;

/**
 * Represents a file that has been read to memory by the parser.
 * TODO: Finish the description of this interface
 *
 * @author Kyer Potts
 */
public class ParserFile implements FileParserElement {

  @Override
  public String getName() {
    // TODO: Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getName'");
  }

  @Override
  public boolean isDirectory() {
    // TODO: Auto-generated method stub
    throw new UnsupportedOperationException(
        "Unimplemented method 'isDirectory'");
  }

  @Override
  public void parse(ReportComposite report) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'parse'");
  }
}
