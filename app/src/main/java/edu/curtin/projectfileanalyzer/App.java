package edu.curtin.projectfileanalyzer;

import edu.curtin.projectfileanalyzer.directoryparser.ParserDirectory;
import edu.curtin.projectfileanalyzer.directoryparser.ParserFile;
import edu.curtin.projectfileanalyzer.directoryvalidator.DirectoryValidator;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * This program analyzes a user input directory for various content, and
 * displays it in an easily readable format to the user.
 * It achieves this by creating two object trees that represent the
 * functionality of the program in different states.
 * The first is the DirectoryParser tree. This tree uses the composite pattern
 * to build a representation of the directory structure in memory, and provides
 * a method to parse through the directory structure searching for criteria
 * matches.
 * The second is the Report tree. This tree also uses the composite pattern to
 * represent the directory structure of folders and files that ONLY contain
 * criteria matches. This allows the report to accurately display analytics
 * information depending upon the format type chosen by the user.
 */
public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    /**
     * Main entry point for the ProjectFileAnalyzer program
     *
     * @param args user supplied arguments that should point to a directory within
     *             the system
     */
    public static void main(String[] args) {
        // A rootFile object is required in order to build the parser. It is set to
        // null initially as user arguments must first be validated.
        File rootFile = null;

        // DirectoryValidator ensures user supplied arguments are correct and can be
        // used within the program
        DirectoryValidator dirValidator = new DirectoryValidator();

        AnalyzerMenu menu;
        boolean executeProgram = false; // Determines whether program can continue without major errors

        try {
            // Retrieve the path provided by the user or set default path (current
            // directory)
            rootFile = new File(dirValidator.determinePath(args));
            LOGGER.info(
                    () -> "User supplied either no arguments or a single argument in accordance with program requirements");
            // Check that path points to valid directory
            executeProgram = dirValidator.isValidPath(rootFile);
        } catch (DirectoryPathException e) {
            // The use has entered to many args when executing the program
            LOGGER.severe(() -> "User has entered an invalid directory path: " +
                    args.toString());
            System.out.println("FATAL ERROR: " + e.getMessage());
        }

        // Required to read user input throughout the program
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            // The program has found a valid path and the program can execute
            // accordingly
            if (executeProgram == true) {
                LOGGER.info(
                        () -> "User supplied argument points to a valid directory for use within the rest of the program");
                LOGGER.info(() -> "Program has entered main execution state.");
                System.out.println("Building Parser tree...");
                // Create a root directory for the parser
                ParserDirectory rootParser = new ParserDirectory(rootFile.getName());
                LOGGER.info(() -> "Root parser successfully initialised");
                // Build the rest of the parser tree structure from the parser root
                buildFileParser(rootFile, rootParser);
                System.out.println("Parser tree complete!");
                LOGGER.info(() -> "Parser tree successfully initialised");

                // The menu should only be executed once the parser has finished
                // being built
                menu = new AnalyzerMenu(input);
                menu.executeMenu(rootParser);
            } else {
                LOGGER.info(
                        () -> "User supplied argument does not point to a valid directory");
                System.out.println(
                        "Provided path does not point to a directory on this system: " +
                                rootFile.getName());
                System.out.println("Please check the path and try again.");
            }

        } catch (IOException e) {
            LOGGER.severe(() -> e.getMessage());
        }
        LOGGER.info("Input reader successfully closed.");
        LOGGER.info("Program exiting.");
    }

    /**
     * Builds a FileParser tree structure recursively
     *
     * @param directory the current File object that the function is recursing
     *                  over. This must always be a directory.
     * @param parent    The current ParserDirectory that the function is
     *                  recursively adding new children to.
     */
    private static void buildFileParser(File directory, ParserDirectory parent) {
        LOGGER.info(() -> "Building parser directory " + parent.getName() + ".");
        // Iterates through the files contained within the directory given and
        // creates a ParserFile and adds the content of the file, or creates a
        // ParserDirectory and calls the buildFileParser function recursively to
        // continue generating the tree structure. This ensures that all files and
        // directories contained within root are built into the FileParserComposite
        // tree.
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                ParserFile newParserFile = new ParserFile(file.getName());
                try {
                    newParserFile.addFileData(file);
                } catch (IOException e) {
                    LOGGER.warning(() -> e.getMessage());
                    System.out.println(
                            "Problem adding data to" + newParserFile.getName() +
                                    " occured. Parser data may not be accurate for reporting purposes.");
                }
                parent.addChild(newParserFile);
            } else if (file.isDirectory()) {
                ParserDirectory newParent = new ParserDirectory(file.getName());
                parent.addChild(newParent);
                // Calls this function recursively on the currently obtained File
                // and newly created ParentDirectory to ensure that the
                // FileParserComposite tree is completely built
                buildFileParser(file, newParent);
            }
        }
        LOGGER.info(
                () -> "Parser directory tree " + parent.getName() + " completed.");
    }
}
