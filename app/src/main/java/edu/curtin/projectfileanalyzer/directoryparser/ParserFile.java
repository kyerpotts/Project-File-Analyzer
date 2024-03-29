package edu.curtin.projectfileanalyzer.directoryparser;

import edu.curtin.projectfileanalyzer.matcher.CriteriaMatcher;
import edu.curtin.projectfileanalyzer.report.ReportComposite;
import edu.curtin.projectfileanalyzer.report.ReportFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents a file that has been read to memory by the parser.
 *
 * @author Kyer Potts
 */
public class ParserFile implements FileParserComposite {
  private static final Logger LOGGER = Logger.getLogger(ParserFile.class.getName());
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
    lines = new ArrayList<>();
    LOGGER.info(() -> "Parser File: " + this.name + " successfully created");
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ReportComposite parse(CriteriaMatcher criteriaMatcher) {
    ReportFile reportFile = new ReportFile(this.name);

    for (Line line : lines) {
      if (criteriaMatcher.includeLine(line)) {
        reportFile.addLine(line);
        LOGGER.info(() -> "Line " + line.getNumber() +
            " in file: " + this.name +
            " matches given criteria. Added to report.");
      }
    }

    return reportFile;
  }

  /**
   * Reads through a file and adds the data contained within to the lines data
   * structure
   *
   * @param file an object that contains a reference to a file and all of the
   *             data contained within
   */
  public void addFileData(File file) throws IOException {
    try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
      int lineNumber = 0; // Initialised at 0 in case the file is empty
      String lineContent;
      // If the file is a log file, and the program is analyzing itself, it may
      // run forever as new logs are added to the file.
      // TODO: Remove this if statement when shipping project.
      if (!file.getName().contains(".log")) {
        // Reads lines from the file into lineContent until all of the file
        // contents have been read
        while ((lineContent = fileReader.readLine()) != null) {
          lineNumber++; // lineNumber must be incremented to accurately
                        // represent the line location
          addNewLine(lineNumber, lineContent);
        }
      }
    } catch (IOException e) {
      LOGGER.warning(
          () -> "Could not read contents of file: " + this.name +
              ". Parser will not accurately depict complete file tree.");
      throw e;
    }
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
    LOGGER.info(
        () -> "Line: " + lineNumber + " succesfully added in " + this.name);
  }
}
