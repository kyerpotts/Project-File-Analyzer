package edu.curtin.projectfileanalyzer.matcher;

import edu.curtin.projectfileanalyzer.directoryparser.Line;

/**
 * Implentation class for a matcher object that matches against text strings
 *
 * @author Kyer Potts
 */
public class TextMatcher implements LineMatcher {
  private String matchString;

  /**
   * @param matchString contains the string that must be matched against line
   *                    contents substrings
   */
  public TextMatcher(String matchString) {
    this.matchString = matchString;
  }

  @Override
  public boolean match(Line line) {
    // A positive match must be returned if a matching substring is found in
    // order to determine exclusion/inclusion in the report
    return line.getContent().contains(matchString);
  }
}
