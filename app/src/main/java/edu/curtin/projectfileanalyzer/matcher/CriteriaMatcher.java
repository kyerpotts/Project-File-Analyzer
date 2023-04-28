package edu.curtin.projectfileanalyzer.matcher;

import edu.curtin.projectfileanalyzer.directoryparser.Line;
import java.util.List;
import java.util.Map;

public class CriteriaMatcher {
  private Map<String, List<LineMatcher>> matcherMap;

  /**
   * Constructor instantiates CriteriaMatcher with Map built by MatcherBuilder
   *
   * @param matcherMap Contains all of the lineMatchers generated from user
   *                   entered criteria
   */
  public CriteriaMatcher(Map<String, List<LineMatcher>> matcherMap) {
    this.matcherMap = matcherMap;
  }

  /**
   * Method determines whether line provided should be included within the
   * report
   *
   * @param line line to be tested for inclusion
   * @return True if the line is to be included, false if it is to be excluded
   */
  public boolean includeLine(Line line) {
    // Check the exclusion matchers first. If an exclude is found the line must
    // be disqualified, even if there are inclusion matches within the line
    for (LineMatcher matcher : matcherMap.get("e")) {
      if (matcher.match(line)) {
        return false;
      }
    }
    // Check the inclusion matchers after the exclusion matchers. If any
    // inclusion matches are found, the line must be included in the report
    for (LineMatcher matcher : matcherMap.get("i")) {
      if (matcher.match(line)) {
        return true;
      }
    }
    // If no exclusion or inclusion matches found, the line is automatically
    // disqualified
    return false;
  }
}
