package edu.curtin.projectfileanalyzer.directoryparser;

/**
 * Contains information related to a single line located within a file. Allows
 * line meta data to be
 * tracked and retrieved instead of just the content.
 *
 * @author Kyer Potts
 */
public class Line {
  private int number;
  private String content;

  /**
   * Initialises all values within the object necessary to represent a line
   * (string of data)
   *
   * @param lineNumber  Indicates where in the file that the line exists
   * @param lineContent The content String of the line. This should be stripped
   *                    of illegal characters "\n" an "\r"
   */
  public void init(int lineNumber, String lineContent) {
    number = lineNumber;
    // Ensures that
    lineContent.replace("\n", "");
    lineContent.replace("\r", "");
    content = lineContent;
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
