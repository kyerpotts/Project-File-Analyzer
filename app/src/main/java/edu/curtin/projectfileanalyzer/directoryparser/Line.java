package edu.curtin.projectfileanalyzer.directoryparser;

import java.util.logging.Logger;

/**
 * Contains information related to a single line located within a file. Allows
 * line meta data to be
 * tracked and retrieved instead of just the content.
 *
 * @author Kyer Potts
 */
public class Line {
  private static final Logger LOGGER = Logger.getLogger(Line.class.getName());
  private int number;
  private String content;

  /**
   * Constructor initialises all values within the object necessary to represent
   * a line (string of data)
   *
   * @param lineNumber  Indicates where in the file that the line exists
   * @param lineContent The content String of the line. This should be stripped
   *                    of illegal characters "\n" an "\r"
   */
  public Line(int lineNumber, String lineContent) {
    number = lineNumber;
    // Ensures that there are no new line or carriage return characters within
    // the string
    lineContent.replace("\n", "");
    lineContent.replace("\r", "");
    content = lineContent;
    LOGGER.info(() -> "Line at: " + number + " successfully created");
  }

  /**
   * @return The line number that the line content exists on within the file
   */
  public int getNumber() {
    return number;
  }

  /**
   * @return The text content of the line
   */
  public String getContent() {
    return content;
  }
}
