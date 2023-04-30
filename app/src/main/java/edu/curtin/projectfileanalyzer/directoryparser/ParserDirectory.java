package edu.curtin.projectfileanalyzer.directoryparser;

import edu.curtin.projectfileanalyzer.matcher.CriteriaMatcher;
import edu.curtin.projectfileanalyzer.report.ReportComposite;
import edu.curtin.projectfileanalyzer.report.ReportDirectory;
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
  public String getName() {
    return this.name;
  }

  @Override
  public ReportComposite parse(CriteriaMatcher criteriaMatcher) {
    ReportDirectory reportDirectory = new ReportDirectory(this.getName());
    // Parse recursively through all children to continue building the Report
    // tree
    for (FileParserComposite parser : children) {
      ReportComposite reportChild = parser.parse(criteriaMatcher);
      // A child should only be added to the parent directory if it contains
      // lines within its substructure
      if (reportChild.getSize() > 0) {
        reportDirectory.addChild(reportChild);
        LOGGER.info(
            () -> "Child: " + reportChild.getName() +
                " is valid size. Parser has added to children of " +
                reportDirectory.getName());
      }
    }
    // The reportDirectory is always returned even if it is empty. This allows
    // the root folder to be reported even if it is empty
    return reportDirectory;
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
        () -> child.getName() + " successfully added to parent: " + this.name);
  }
}
