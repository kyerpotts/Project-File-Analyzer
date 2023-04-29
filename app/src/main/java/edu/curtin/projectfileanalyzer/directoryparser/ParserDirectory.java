package edu.curtin.projectfileanalyzer.directoryparser;

import edu.curtin.projectfileanalyzer.report.ReportComposite;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * ParserDirectory is the representation of a file directory utilized for
 * parsing when generating a report.
 *
 * @author Kyer Potts
 */
public class ParserDirectory implements FileParserComposite {
  // TODO: Log appropriately through the methods contained in this class
  private static final Logger LOGGER = Logger.getLogger(ParserDirectory.class.getName());
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
    LOGGER.info(
        () -> "Parser Directory: " + this.name + " successfully created");
  }

  @Override
  public String getValue() {
    return this.name;
  }

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
    LOGGER.info(
        () -> child.getValue() + " successfully added to parent: " + this.name);
  }
}
