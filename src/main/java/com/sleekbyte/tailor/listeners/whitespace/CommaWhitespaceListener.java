package com.sleekbyte.tailor.listeners.whitespace;

import com.sleekbyte.tailor.antlr.SwiftBaseListener;
import com.sleekbyte.tailor.antlr.SwiftParser;
import com.sleekbyte.tailor.common.Messages;
import com.sleekbyte.tailor.common.Rules;
import com.sleekbyte.tailor.output.Printer;
import com.sleekbyte.tailor.utils.ParseTreeUtil;
import com.sleekbyte.tailor.utils.WhitespaceVerifier;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

/**
 * Flags commas that are not left associated.
 */
public class CommaWhitespaceListener extends SwiftBaseListener {

    private WhitespaceVerifier verifier;

    public CommaWhitespaceListener(Printer printer) {
        this.verifier = new WhitespaceVerifier(printer, Rules.COMMA_WHITESPACE);
    }

    @Override
    public void enterTypeInheritanceClause(SwiftParser.TypeInheritanceClauseContext ctx) {
        if (ctx.classRequirement() != null && ctx.typeInheritanceList() != null) {
            Token left = ParseTreeUtil.getStopTokenForNode(ctx.classRequirement());
            Token right = ParseTreeUtil.getStartTokenForNode(ctx.typeInheritanceList());
            Token comma = ((TerminalNodeImpl) ctx.getChild(2)).getSymbol();

            verifyCommaLeftAssociation(left, right, comma);
        }

        if (ctx.typeInheritanceList() != null) {
            checkWhitespaceAroundCommaSeparatedList(ctx.typeInheritanceList());
        }
    }

    @Override
    public void enterGenericParameterList(SwiftParser.GenericParameterListContext ctx) {
        checkWhitespaceAroundCommaSeparatedList(ctx);
    }

    @Override
    public void enterRequirementList(SwiftParser.RequirementListContext ctx) {
        checkWhitespaceAroundCommaSeparatedList(ctx);
    }

    private void checkWhitespaceAroundCommaSeparatedList(ParserRuleContext ctx) {
        for (int i = 0; i < ctx.children.size() - 2; i += 2) {
            Token left = ParseTreeUtil.getStopTokenForNode(ctx.getChild(i));
            Token right = ParseTreeUtil.getStartTokenForNode(ctx.getChild(i + 2));
            Token comma = ((TerminalNodeImpl) ctx.getChild(i + 1)).getSymbol();

            verifyCommaLeftAssociation(left, right, comma);
        }
    }

    private void verifyCommaLeftAssociation(Token left, Token right, Token comma) {
        verifier.verifyPunctuationLeftAssociation(left, right, comma, Messages.COMMA);
    }
}
