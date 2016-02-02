package com.sleekbyte.tailor.format;

import com.sleekbyte.tailor.common.ColorSettings;
import com.sleekbyte.tailor.common.ExitCode;
import com.sleekbyte.tailor.output.ViolationMessage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Formatter used to display violation messages.
 * New formats can be added by extending this abstract base class.
 */
public abstract class Formatter {

    protected File inputFile;
    protected ColorSettings colorSettings;

    public Formatter(File inputFile, ColorSettings colorSettings) {
        this.inputFile = inputFile;
        this.colorSettings = colorSettings;
    }

    /**
     * Print all violation messages for a given file to the console.
     *
     * @param violationMessages list of violation messages to print
     * @throws IOException if canonical path could not be retrieved from the inputFile
     */
    public abstract Map<String, Object> displayViolationMessages(List<ViolationMessage> violationMessages)
                                        throws IOException;

    /**
     * Print a message to the console indicating that the file failed to be parsed.
     * @throws IOException if canonical path could not be retrieved from the inputFile
     */
    public abstract Map<String, Object> displayParseErrorMessage() throws IOException;

    /**
     * Print a message to the console stating the analysis and violation statistics for a given number of files.
     *
     * @param numFiles number of files to be analyzed
     * @param numSkipped number of files that could not be parsed successfully
     * @param numErrors number of errors detected during analysis
     * @param numWarnings number of warnings detected during analysis
     */
    public abstract void displaySummary(long numFiles, long numSkipped, long numErrors, long numWarnings);

    /**
     * Determine an appropriate exit code for the application, depending on the number of errors and warnings found.
     *
     * @param numErrors number of errors detected during analysis
     * @return the ExitCode reflecting the application's status determined by the results of the analysis run
     */
    public ExitCode getExitStatus(long numErrors) {
        if (numErrors >= 1L) {
            return ExitCode.FAILURE;
        }
        return ExitCode.SUCCESS;
    }

    protected static String pluralize(long number, String singular, String plural) {
        return String.format("%d %s", number, number == 1 ? singular : plural);
    }

}
