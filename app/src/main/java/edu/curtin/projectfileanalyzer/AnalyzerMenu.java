package edu.curtin.projectfileanalyzer;

import edu.curtin.projectfileanalyzer.directoryparser.FileParserComposite;
import edu.curtin.projectfileanalyzer.matcher.CriteriaMatcher;
import edu.curtin.projectfileanalyzer.matcher.MatcherBuilder;
import edu.curtin.projectfileanalyzer.report.CountReport;
import edu.curtin.projectfileanalyzer.report.ReportComposite;
import edu.curtin.projectfileanalyzer.report.ReportType;
import edu.curtin.projectfileanalyzer.report.ShowReport;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * This class holds all of the required functionality for displaying a program
 * menu to the user and executing options selected by the user
 *
 * @author Kyer Potts
 */
public class AnalyzerMenu {
  private static final Logger LOGGER = Logger.getLogger(AnalyzerMenu.class.getName());
  private BufferedReader input;

  public AnalyzerMenu(BufferedReader input) {
    this.input = input;
  }

  /**
   * Executes the program menu, allowing the user to interact with the program
   * and it's functionality
   *
   * @param fileParser The root of the FileParserComposite that will have been
   *                   built prior to any interaction options being given to the
   *                   user
   */
  public void executeMenu(FileParserComposite fileParser) {
    String userInput = null;
    // Used to control program flow depending on user request
    boolean continueMenuLoop = true;
    while (continueMenuLoop) {
      // Display available options to the user
      printMenuOptions();
      try {
        userInput = input.readLine();

      } catch (IOException e) {
        LOGGER.warning(() -> "User input error: " + e.getMessage());
      }
      // Guard condition ensures switch case is not reading a null value from
      // the userInput variable
      if (userInput == null) {
        userInput = "";
      }

      // These could be dependency injected, but there is really no point
      // injecting into this class as it dictates the majority of the control
      // flow of the program
      CriteriaMatcher criteriaMatcher = null;
      ReportType reportType = null;

      switch (userInput) {
        case "1":
          LOGGER.info(() -> "User has selected to add criteria");
          criteriaMatcher = addCriteria(input);
          break;
        case "2":
          LOGGER.info(() -> "User has selected to choose report type");
          reportType = chooseReportType(input);
          break;
        case "3":
          LOGGER.info(() -> "User has selected to display report");
          try {
            displayReport(fileParser, criteriaMatcher, reportType);
          } catch (InvalidReportArgumentsException e) {
            System.out.println(e.getMessage());
            LOGGER.warning(
                "User attempted to display report without initialising components: " +
                    e.getMessage());
          }
          break;
        case "4":
          LOGGER.info(() -> "User has selected to exit program");
          // Flow control variable is set to false, as the user has requested to
          // quit. This will exit the loop and the program will end
          continueMenuLoop = false;
          break;
        default:
          LOGGER.info(() -> "User has selected an invalid option");
          // Either the user has entered an invalid option, or a dummy option has
          // been entered due to an error
          System.out.println("Invalid menu option");
          break;
      }
    }
    // Goodbye message is nice to have
    System.out.println("Exiting Project File Analyzer. Goodbye.");
  }

  /**
   * This method builds and then displays a report to the user.
   *
   * @param criteriaMatcher
   * @param reportType
   */
  private void displayReport(FileParserComposite fileParser,
      CriteriaMatcher criteriaMatcher,
      ReportType reportType)
      throws InvalidReportArgumentsException {
    // Guard condition prevents uninitialised CriteriaMatcher from being
    // used
    if (criteriaMatcher == null) {
      throw new InvalidReportArgumentsException(
          "Error has occured. Cannot generate a report if no criteria have been given");
    }
    // Guard condition prevents uninitialised ReportType from being used
    if (reportType == null) {
      throw new InvalidReportArgumentsException(
          "Error has occured. Cannot generate a report if no report type has been given");
    }

    // Building a full directory tree in memory may take a while, displaying a
    // message to the user increases usability
    System.out.println("Building report, please wait...");
    ReportComposite reportRoot = fileParser.parse(criteriaMatcher);
    // Once the report has been built, display immediately to the screen
    reportRoot.writeReport(reportType);
  }

  /**
   * Prints out the available menu options for the use
   */
  private void printMenuOptions() {
    System.out.println("Please choose from the following options:");
    System.out.println("1: Set Report Criteria");
    System.out.println("2: Set Report Output Format");
    System.out.println("3: Generate Report");
    System.out.println("4: Quit");
  }

  /**
   * Method allows the user to add criteria to the report
   *
   * @param input An input reader to allow the user to enter criteria from the
   *              keyboard.
   * @return CriteriaMatcher with completed LineMatcher objects based on user
   *         entered criteria.
   */
  private CriteriaMatcher addCriteria(BufferedReader input) {
    MatcherBuilder matcherBuilder = new MatcherBuilder();
    // Request criteria from the user
    matcherBuilder.requestMatcherInput(input);
    // The CriteriaMatcher object must be returned so it can be given to the
    // ReportType object
    return matcherBuilder.buildCriteriaMatcher();
  }

  /**
   * Allows the user to select which format the report will be displayed in
   *
   * @param input An input reader to allows the user to enter a selection based
   *              on the options available
   * @return An initialised ReportType, or null if the user chooses to exit
   *         before making a selection
   */
  private ReportType chooseReportType(BufferedReader input) {
    ReportType chosenReportType = null;
    boolean chosen = false;
    // Using Strings for switch case statements results in less exception types
    // being thrown when matching user input
    String selection = null;

    while (!chosen) {
      System.out.println("Please choose from the following");
      System.out.println("1: Count Report");
      System.out.println("2: Show Report");
      System.out.println("3: Return to main menu");

      try {
        selection = input.readLine();

      } catch (IOException e) {
        System.out.println(
            "An error occured while entering a selection. Please try again.");
        LOGGER.warning(() -> "An error occurred with BufferedReader input.");
      }

      // Allows the user to make a selection of available options.
      switch (selection) {
        case "1":
          // User has selected a Count Report
          chosenReportType = new CountReport();
          LOGGER.info(() -> "User has selected to view CountReport format");
          chosen = true;
          break;
        case "2":
          // User has selected a Show Report
          chosenReportType = new ShowReport();
          LOGGER.info(() -> "User has selected to view ShowReport format");
          chosen = true;
          break;
        case "3":
          // User has decided to return to the main menu
          chosen = true;
          LOGGER.info(() -> "User has selected to return to menu");
          break;
        default:
          // Control flow should not change with an invalid option. Loop should
          // continue
          System.out.println("Invalid option. Please try again.");
          LOGGER.info(() -> "User has selected an invalid option");
          break;
      }
    }

    return chosenReportType;
  }
}
