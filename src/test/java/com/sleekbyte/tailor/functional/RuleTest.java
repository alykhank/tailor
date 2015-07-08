package com.sleekbyte.tailor.functional;

import static org.junit.Assert.assertArrayEquals;

import com.sleekbyte.tailor.Tailor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for functional rule tests.
 */
public abstract class RuleTest {

    protected static final String TEST_INPUT_DIR = "src/test/swift/com/sleekbyte/tailor/functional/";
    protected static final String NEWLINE_REGEX = "\\r?\\n";

    protected ByteArrayOutputStream outContent;
    protected File inputFile;
    protected List<String> expectedMessages;

    protected abstract void addAllExpectedMsgs();

    protected String getInputFilePath() {
        return String.format("%s.swift", this.getClass().getSimpleName());
    }

    @Before
    public void setUp() throws UnsupportedEncodingException {
        inputFile = new File(TEST_INPUT_DIR + getInputFilePath());
        expectedMessages = new ArrayList<>();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, false, Charset.defaultCharset().name()));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void testRule() throws IOException {
        String[] command = getCommandArgs();
        addAllExpectedMsgs();

        Tailor.main(command);

        List<String> actualOutput = new ArrayList<>();
        int lineNum = 0;
        for (String msg : outContent.toString(Charset.defaultCharset().name()).split(NEWLINE_REGEX)) {
            // Skip the first two lines for file header
            if (lineNum++ < 2) {
                continue;
            }
            String truncatedMsg = msg.substring(msg.indexOf(inputFile.getName()));
            actualOutput.add(truncatedMsg);
        }

        assertArrayEquals(outContent.toString(Charset.defaultCharset().name()), this.expectedMessages.toArray(),
            actualOutput.toArray());
    }

    protected String[] getCommandArgs() {
        return new String[]{
            inputFile.getPath()
        };
    }

}
