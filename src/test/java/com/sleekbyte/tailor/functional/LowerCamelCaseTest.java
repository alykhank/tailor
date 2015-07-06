package com.sleekbyte.tailor.functional;

import com.sleekbyte.tailor.common.Messages;
import com.sleekbyte.tailor.common.Severity;
import com.sleekbyte.tailor.output.Printer;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Functional tests for lower camel case rule.
 */
@RunWith(MockitoJUnitRunner.class)
public class LowerCamelCaseTest extends RuleTest {

    @Override
    protected void addAllExpectedMsgs() {
        addExpectedMsg(2, 9, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(7, 5, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(8, 5, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(10, 6, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(10, 10, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(16, 14, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(20,22, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(26, 8, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(26, 108, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(30, 19, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(46, 16, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(50, 20, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(54, 5, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(55, 10, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(56, 9, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(63, 5, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(63, 8, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(64, 5, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(69, 9, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(70, 9, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(73, 5, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(76, 9, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(79, 17, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(84, 17, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
        addExpectedMsg(90, 9, Severity.WARNING, Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE);
    }

    private void addExpectedMsg(int line, int column, Severity severity, String msg) {
        this.expectedMessages.add(Printer.genOutputStringForTest(inputFile.getName(), line, column, severity, msg));
    }

}
