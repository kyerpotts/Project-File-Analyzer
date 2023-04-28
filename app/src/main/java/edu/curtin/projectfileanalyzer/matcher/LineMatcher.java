package edu.curtin.projectfileanalyzer.matcher;

import edu.curtin.projectfileanalyzer.directoryparser.Line;

/**
 * Provides a contract to various matcher types for the purpose of creating
 * reports
 *
 * @author Kyer Potts
 */
public interface LineMatcher {

  /**
   * Matches the line from the parser against user given criteria to determine
   * if it should be included in the report
   *
   * @param line is the current line within the ParserFile object that is being
   *             tested to determine inclusion
   * @return true on a positive match, false on a negative match
   */
  public boolean match(Line line);
}
