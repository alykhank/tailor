package com.sleekbyte.tailor.output;

import com.sleekbyte.tailor.common.Location;
import com.sleekbyte.tailor.common.Rules;
import com.sleekbyte.tailor.common.Severity;
import com.sleekbyte.tailor.format.Formatter;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Generates and outputs formatted analysis messages for Xcode.
 */
public final class Printer implements AutoCloseable {

    private File inputFile;
    private Severity maxSeverity;
    private Formatter formatter;
    private boolean lastFile;
    private Map<String, ViolationMessage> msgBuffer = new HashMap<>();
    private Set<Integer> ignoredLineNumbers = new HashSet<>();

    /**
     * Constructs a printer for the specified input file, maximum severity, and color setting.
     *
     * @param inputFile The source file to verify
     * @param maxSeverity The maximum severity of any emitted violation messages
     * @param formatter Format to print in
     */
    public Printer(File inputFile, Severity maxSeverity, Formatter formatter, boolean lastFile) {
        this.inputFile = inputFile;
        this.maxSeverity = maxSeverity;
        this.formatter = formatter;
        this.lastFile = lastFile;
    }

    /**
     * Prints warning message.
     *
     * @param rule Rule associated with warning
     * @param warningMsg Warning message to print
     * @param location Location object containing line and column number for printing
     */
    public void warn(Rules rule, String warningMsg, Location location) {
        print(rule, Severity.WARNING, warningMsg, location);
    }

    /**
     * Prints error message.
     *
     * @param rule Rule associated with error
     * @param errorMsg Error message to print
     * @param location Location object containing line and column number for printing
     */
    public void error(Rules rule, String errorMsg, Location location) {
        print(rule, Severity.min(Severity.ERROR, maxSeverity), errorMsg, location);
    }

    private void print(Rules rule, Severity severity, String msg, Location location) {
        ViolationMessage violationMessage = new ViolationMessage(rule, location.line, location.column, severity, msg);
        try {
            violationMessage.setFilePath(this.inputFile.getCanonicalPath());
        } catch (IOException e) {
            System.err.println("Error in getting canonical path of input file: " + e.getMessage());
        }
        this.msgBuffer.put(violationMessage.toString(), violationMessage);
    }

    // Visible for testing only
    public static String genOutputStringForTest(Rules rule, String filePath, int line, Severity severity, String msg) {
        return new ViolationMessage(rule, filePath, line, 0, severity, msg).toString();
    }

    // Visible for testing only
    public static String genOutputStringForTest(Rules rule, String filePath, int line, int column, Severity severity,
                                                String msg) {
        return new ViolationMessage(rule, filePath, line, column, severity, msg).toString();
    }

    public List<ViolationMessage> getViolationMessages() {
        return new ArrayList<>(this.msgBuffer.values());
    }

    @Override
    public void close() throws IOException {
        List<ViolationMessage> outputList = new ArrayList<>(this.getViolationMessages().stream()
            .filter(msg -> !ignoredLineNumbers.contains(msg.getLineNumber())).collect(Collectors.toList()));
        Collections.sort(outputList);
        formatter.displayViolationMessages(outputList, lastFile);
    }

    private long getNumMessagesWithSeverity(Severity severity) {
        return msgBuffer.values().stream()
            .filter(msg -> !ignoredLineNumbers.contains(msg.getLineNumber()))
            .filter(msg -> msg.getSeverity().equals(severity)).count();
    }

    public long getNumErrorMessages() {
        return getNumMessagesWithSeverity(Severity.ERROR);
    }

    public long getNumWarningMessages() {
        return getNumMessagesWithSeverity(Severity.WARNING);
    }

    public void ignoreLine(int ignoredLineNumber) {
        this.ignoredLineNumbers.add(ignoredLineNumber);
    }

    /**
     * Print all rules along with their descriptions to STDOUT.
     */
    public static void printRules() {
        Rules[] rules = Rules.values();

        AnsiConsole.out.println(Ansi.ansi().render(String.format("@|bold %d rules available|@%n", rules.length)));
        for (Rules rule : rules) {
            AnsiConsole.out.println(Ansi.ansi().render(String.format("@|bold %s|@%n"
                + "@|underline Description:|@ %s%n"
                + "@|underline Style Guide:|@ %s%n", rule.getName(), rule.getDescription(), rule.getLink())));
        }
    }

    public void printParseErrorMessage() throws IOException {
        formatter.displayParseErrorMessage();
    }
}
