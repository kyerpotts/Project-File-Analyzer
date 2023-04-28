package edu.curtin.projectfileanalyzer.matcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This class is responsible for generating a list of matcher objects based upon
 * user input, and serves as a wrapper for LineMatchers to determine whether a
 * line located within a ParserFile object should be included within the report.
 *
 * @author Kyer Potts
 */
public class MatcherBuilder {
  private static final Logger LOGGER = Logger.getLogger(MatcherBuilder.class.getName());
  private List<String> criteriaList;
  private Map<String, List<LineMatcher>> matcherMap;

  public MatcherBuilder() {
    this.criteriaList = new ArrayList<>();
    this.matcherMap = new HashMap<>();
  }

  /**
   * Prompts the user to enter report criteria and stores all entered criteria
   * within a list
   *
   * @param input A buffered reader that takes input from System.in.
   */
  public void requestMatcherInput(BufferedReader input) {
    criteriaList.clear(); // The criteria list must be refreshed whenever more
                          // input is requested
    System.out.println(
        "Please enter criteria in the form: i t text to match / e r [aeiou].");
    System.out.println("When you are finished entering criteria, press enter.");

    // Continue recieving input from the user until enter is pressed without any
    // additional characters
    boolean recieveInput = true;
    while (recieveInput) {
      String criteria = "";

      try {
        criteria = input.readLine();
      } catch (IOException e) {
        LOGGER.warning(() -> "Error receiving report criteria input.");
      }

      if (criteria.equals("")) {
        LOGGER.info(() -> "User has finished entering criteria.");
        recieveInput = false; // Stop asking the user for input
      } else {
        criteriaList.add(criteria);
        LOGGER.info(() -> "User has added: " +
            criteriaList.get(criteriaList.size() - 1) +
            " to criteria list.");
      }
    }
  }

  /**
   * Method builds a CriteriaMatcher object with appropriate Map of
   * LineMatchers.
   *
   * @return Completed CriteriaMatcher object with safe LineMatcher objects.
   */
  public CriteriaMatcher buildCriteriaMatcher() {
    // The matcherMap keys must have new values instantiated to ensure no old
    // matcher objects are kept
    matcherMap.put("e", new ArrayList<>());
    matcherMap.put("i", new ArrayList<>());

    for (String criteria : criteriaList) {
      String[] parsedCriteria;
      // Break criteria string up into component parts
      parsedCriteria = criteria.split(" ");

      // If invalid input String was given, ignore it
      if (validateInput(parsedCriteria) == false) {
        System.out.println("Invalid criteria input: " + criteria +
            ". Ignoring criteria.");
        LOGGER.warning(() -> "Invalid criteria entered: " + criteria);
      } else {
        // The input was valid so it can be safely inserted
        insertCriteria(parsedCriteria);
        LOGGER.info(() -> "Criteria successfully added: " + criteria);
      }
    }

    return new CriteriaMatcher(matcherMap);
  }

  /**
   * Method validates the input given by the user to ensure it can be properly
   * parsed to create matcher objects
   *
   * @param criteriaInput The array of user entered criteria to be checked for
   *                      validity
   * @return True if the criteria is valid
   */
  private boolean validateInput(String[] criteriaInput) {
    // The criteriaInput array is never null or empty
    assert criteriaInput != null;
    assert criteriaInput.length != 0;

    boolean includeCriteria = true;

    // Guard against invalid input size
    if (criteriaInput.length != 3) {
      includeCriteria = false;
    }

    // Guard against invalid input type
    if (!(criteriaInput[0].equals("e") || criteriaInput[0].equals("i"))) {
      includeCriteria = false;
    }

    // Guard condition for invalid input matcher
    if (!(criteriaInput[0].equals("e") || criteriaInput[0].equals("i"))) {
      includeCriteria = false;
    }
    return includeCriteria;
  }

  /**
   * Inserts valid user entered criteria into the appropriate entry within the
   * matcherMap object
   *
   * @param parsedCriteria The array of user entered criteria that is to be
   *                       entered into the map
   */
  private void insertCriteria(String[] parsedCriteria) {
    // Create a text matcher or a regex matcher depending on the value entered
    // by the user
    if (parsedCriteria[1].equals("t")) {
      // Insert a new text matcher into the matcherMap
      matcherMap.get(parsedCriteria[0]).add(new TextMatcher(parsedCriteria[2]));

      LOGGER.info(() -> "Text matcher criteria " + parsedCriteria[0] + " " +
          parsedCriteria[2] + " created.");
    } else if (parsedCriteria[1].equals("r")) {
      // Insert a new regex matcher into the matcherMap
      matcherMap.get(parsedCriteria[0])
          .add(new RegexMatcher(parsedCriteria[2]));

      LOGGER.info(() -> "Regex matcher criteria " + parsedCriteria[0] + " " +
          parsedCriteria[2] + " created.");
    }
  }
}
