package edu.curtin.projectfileanalyzer.directoryparser;

import edu.curtin.projectfileanalyzer.report.ReportComposite;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a file that has been read to memory by the parser.
 *
 * @author Kyer Potts
 */
public class ParserFile implements FileParserComposite {
  private String name;
  private List<Line> lines;

  /**
   * Constructor creates a basic ParserFile object
   *
   * @param name The name of the ParserFile object. This should be the
   *             unqualified path of the File the parser object was created
   *             from
   */
  public ParserFile(String name) {
    this.name = name;
    lines = new ArrayList<Line>();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean isDirectory() {
    return false;
  }

  @Override
  public void parse(ReportComposite report) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'parse'");
  }

  /**
   * Reads through a file and adds the data contained within to the lines data
   * structure
   *
   * @param file an object that contains a reference to a file and all of the
   *             data contained within
   */
  public void addFileData(File file) {
  }

  /**
   * Wrapper method adds a new line to the list of lines
   *
   * @param lineNumber  represents the location of the line in the file
   * @param lineContent contains the string data within a line in a file
   */
  private void addNewLine(int lineNumber, String lineContent) {
    Line newLine = new Line(lineNumber, lineContent);
    lines.add(newLine);
  }
}
