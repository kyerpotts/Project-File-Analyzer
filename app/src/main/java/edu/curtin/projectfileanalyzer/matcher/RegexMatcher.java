package edu.curtin.projectfileanalyzer.matcher;

import edu.curtin.projectfileanalyzer.directoryparser.Line;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implentation class for a matcher object that matches against regex strings
 *
 * @author Kyer Potts
 */
public class RegexMatcher implements LineMatcher {
  private String regexString;

  /**
   * Constructor intializes the matchString variable
   *
   * @param matchRegex contains the regex string that must be matched against
   *                   line contents
   */
  public RegexMatcher(String regexString) {
    this.regexString = regexString;
  }

  @Override
  public boolean match(Line line) {
    Pattern regexPattern = Pattern.compile(regexString);
    Matcher patternMatcher = regexPattern.matcher(line.getContent());

    // A positive match must be returned if a regex pattern is found in
    // order to determine exclusion/inclusion in the report
    return patternMatcher.find();
  }
}
