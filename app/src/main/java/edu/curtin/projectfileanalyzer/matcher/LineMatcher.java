package edu.curtin.projectfileanalyzer.matcher;

import edu.curtin.projectfileanalyzer.directoryparser.Line;

/**
 * TODO: Write a description for this interface
 *
 * @author Kyer Potts
 */
public interface LineMatcher {
  public boolean match(Line line);
}
