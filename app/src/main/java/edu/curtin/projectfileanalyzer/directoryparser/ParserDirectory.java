package edu.curtin.projectfileanalyzer.directoryparser;

import edu.curtin.projectfileanalyzer.report.ReportComposite;
import java.util.ArrayList;
import java.util.List;

/**
 * ParserDirectory is the representation of a file directory utilized for
 * parsing when generating a report.
 *
 * @author Kyer Potts
 */
public class ParserDirectory implements FileParserComposite {
  private String name;
  private List<FileParserComposite> children;

  /**
   * Constructor creates a basic ParserDirectory object
   *
   * @param name The name of the ParserDirectory object. This should be the
   *             unqualified path of the directory the parser object was created
   *             from
   */
  public ParserDirectory(String name) {
    this.name = name;
    children = new ArrayList<>();
  }

  /**
   * Must return unqualified path as the name in the context of the parser
   *
   * @return the name (unqualified path) of this directory
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Must return true for ParserDirectory. This method is used to determine how
   * it is replicated in the report tree structure
   *
   * @return True as is a directory
   */
  @Override
  public boolean isDirectory() {
    // ParserDirectory is a directory
    return true;
  }

  /**
   * Iterates over the list of children and calls the parse method recursively
   * in order to generate the report tree
   *
   * @param report The report object that will be used to generate a report
   *               based on user defined parameters
   */
  @Override
  public void parse(ReportComposite report) {
    // TODO: Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'parse'");
  }

  /**
   * Adds a child to the children list
   *
   * @param child Is the child Composite object to be added to the list of this
   *              directories children
   */
  public void addChild(FileParserComposite child) {
    children.add(child);
  }
}
