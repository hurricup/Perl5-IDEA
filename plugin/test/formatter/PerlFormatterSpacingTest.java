/*
 * Copyright 2015-2021 Alexandr Evstigneev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package formatter;


import org.junit.Test;

import static com.perl5.lang.perl.idea.formatter.settings.PerlCodeStyleSettings.OptionalConstructions.*;
public class PerlFormatterSpacingTest extends PerlFormatterTestCase {
  @Override
  protected String getBaseDataPath() {
    return "testData/formatter/perl/spacing";
  }

  @Test
  public void testPrintArguments() {doFormatTest();}

  @Test
  public void testUseVars() {doFormatTest();}

  @Test
  public void testReplaceRegexWithComments() {doFormatTest();}

  @Test
  public void testRegexExtended() {doFormatTest();}

  @Test
  public void testStringBitwiseOperators() {doFormatTest();}

  @Test
  public void testFunctionLikeExprTrue() {
    getSettings().SPACE_WITHIN_METHOD_CALL_PARENTHESES = true;
    doTestSingleSource("functionLikeExpr");
  }

  @Test
  public void testFunctionLikeExprFalse() {
    getSettings().SPACE_WITHIN_METHOD_CALL_PARENTHESES = false;
    doTestSingleSource("functionLikeExpr");
  }

  @Test
  public void testIsaExpr() {doFormatTest();}

  @Test
  public void testIssue2158() {doFormatTest();}

  @Test
  public void testAsyncSubExpr() {doFormatTest();}

  @Test
  public void testAsyncSubs() {doFormatTest();}

  @Test
  public void testSubModifiers() {doFormatTest();}

  @Test
  public void testSpliceExpr() {doFormatTest();}

  @Test
  public void testScalarExpr() {doFormatTest();}

  @Test
  public void testHeredocInPreviousStatement() {doFormatTest();}

  @Test
  public void testVariableDeclarations() {doFormatTest();}

  @Test
  public void testQuoteLikeWIthLiteralQuotes() {doFormatTest();}

  @Test
  public void testBlockInReplace() {doFormatTest();}

  @Test
  public void testScalarUnary() {doFormatTest();}

  @Test
  public void testUseNoStatements() {doFormatTest();}

  @Test
  public void testIssue1723() {doFormatTest();}

  @Test
  public void testIssue1688() {doFormatTest();}

  @Test
  public void testQwQuotesAndContent() {doFormatTest();}

  @Test
  public void testSpacingAfterReference() {doFormatTest();}

  @Test
  public void testAnonArrayArgument() {doFormatTest();}

  @Test
  public void testAnonHashArgument() {doFormatTest();}

  @Test
  public void testSequenceLike() {doFormatTest();}

  @Test
  public void testSmarterHashFormatting() {doFormatTest();}

  @Test
  public void testMoose() {doFormatTest();}

  @Test
  public void testPolishing() {doFormatTest();}

  @Test
  public void testFormatDeclaration() {doFormatTest();}

  @Test
  public void testNamespaceDefinition() {
    doFormatTest();
  }

  @Test
  public void testSpaceInsideDereferenceTrue() {
    getSettings().SPACE_WITHIN_CAST_PARENTHESES = true;
    doFormatTest();
  }

  @Test
  public void testSpaceInsideDereferenceFalse() {
    getSettings().SPACE_WITHIN_CAST_PARENTHESES = false;
    doFormatTest();
  }

  @Test
  public void testDeclarationsBeforeSignatureTrue() {
    getSettings().SPACE_BEFORE_METHOD_PARENTHESES = true;
    doTestDeclarations();
  }

  @Test
  public void testDeclarationsBeforeSignatureFalse() {
    getSettings().SPACE_BEFORE_METHOD_PARENTHESES = false;
    doTestDeclarations();
  }

  @Test
  public void testDeclarationsWithinSignatureTrue() {
    getSettings().SPACE_WITHIN_METHOD_PARENTHESES = true;
    doTestDeclarations();
  }

  @Test
  public void testDeclarationsWithinSignatureFalse() {
    getSettings().SPACE_WITHIN_METHOD_PARENTHESES = false;
    doTestDeclarations();
  }

  @Test
  public void testDeclarationsWithinEmptySignatureTrue() {
    getSettings().SPACE_WITHIN_EMPTY_METHOD_PARENTHESES = true;
    doTestDeclarations();
  }

  @Test
  public void testDeclarationsWithinEmptySignatureFalse() {
    getSettings().SPACE_WITHIN_EMPTY_METHOD_PARENTHESES = false;
    doTestDeclarations();
  }

  @Test
  public void testDeclarationsBeforeAttributeTrue() {
    getCustomSettings().SPACE_BEFORE_ATTRIBUTE = true;
    doTestDeclarations();
  }

  @Test
  public void testDeclarationsBeforeAttributeFalse() {
    getCustomSettings().SPACE_BEFORE_ATTRIBUTE = false;
    doTestDeclarations();
  }

  private void doTestDeclarations() {
    doTestSingleSource("declarations");
  }

  @Test
  public void testShiftPopSpaceWithinTrue() {
    getSettings().SPACE_WITHIN_METHOD_CALL_PARENTHESES = true;
    doTestShiftLike();
  }

  @Test
  public void testShiftPopSpaceWithinFalse() {
    getSettings().SPACE_WITHIN_METHOD_CALL_PARENTHESES = false;
    doTestShiftLike();
  }

  private void doTestShiftLike() {
    doTestSingleSource("shiftLike");
  }

  @Test
  public void testPerlTidy() {
    initWithPerlTidy();
    assertNoErrorElements();
    doFormatTestWithoutInitialization(getTestName(true), "");
  }

  @Test
  public void testPinxi() {
    initWithPinxi();
    assertNoErrorElements();
    doFormatTestWithoutInitialization(getTestName(true), "");
  }

  @Test
  public void testNewLineAfterComment() {doFormatTest();}

  @Test
  public void testCallArguments() {doFormatTest();}

  @Test
  public void testBlockInGrepMapSort() {doFormatTest();}

  @Test
  public void testBlockInSubExpr() {
    doFormatTest();
  }

  @Test
  public void testContinueBlock() {doFormatTest();}

  @Test
  public void testIssue1607enabled() {
    getSettings().SPACE_BEFORE_IF_PARENTHESES = true;
    doFormatTest();
  }

  @Test
  public void testIssue1607disabled() {
    getSettings().SPACE_BEFORE_IF_PARENTHESES = false;
    doFormatTest();
  }

  @Test
  public void testNewlinesBraceStyleSubsSameLine() {
    getCustomSettings().BRACE_STYLE_SUB = SAME_LINE;
    doTestNewlinesCompounds();
  }

  @Test
  public void testNewlinesBraceStyleSubsNextLine() {
    getCustomSettings().BRACE_STYLE_SUB = NEXT_LINE;
    doTestNewlinesCompounds();
  }

  @Test
  public void testNewlinesBraceStyleCompoundsSameLine() {
    getCustomSettings().BRACE_STYLE_COMPOUND = SAME_LINE;
    doTestNewlinesCompounds();
  }

  @Test
  public void testNewlinesBraceStyleCompoundsNextLine() {
    getCustomSettings().BRACE_STYLE_COMPOUND = NEXT_LINE;
    doTestNewlinesCompounds();
  }

  @Test
  public void testNewlinesBraceStyleNamespaceSameLine() {
    getCustomSettings().BRACE_STYLE_NAMESPACE = SAME_LINE;
    doTestNewlinesCompounds();
  }

  @Test
  public void testNewlinesBraceStyleNamespaceNextLine() {
    getCustomSettings().BRACE_STYLE_NAMESPACE = NEXT_LINE;
    doTestNewlinesCompounds();
  }

  @Test
  public void testNewlinesElseOnNewLineTrue() {
    getCustomSettings().ELSE_ON_NEW_LINE = true;
    doTestNewlinesCompounds();
  }

  @Test
  public void testNewlinesElseOnNewLineFalse() {
    getCustomSettings().ELSE_ON_NEW_LINE = false;
    doTestNewlinesCompounds();
  }

  private void doTestNewlinesCompounds() {
    doTestSingleSource("newlinesCompounds");
  }

  @Test
  public void testSpacingInsideBlockTrue() {
    getSettings().SPACE_WITHIN_BRACES = true;
    doTestSingleSource("spacingInsideBlock");
  }

  @Test
  public void testSpacingInsideBlockFalse() {
    getSettings().SPACE_WITHIN_BRACES = false;
    doTestSingleSource("spacingInsideBlock");
  }

  @Test
  public void testSpacingSemicolonAfterTrue() {
    getSettings().SPACE_AFTER_SEMICOLON = true;
    doTestSemicolon();
  }

  @Test
  public void testSpacingSemicolonAfterFalse() {
    getSettings().SPACE_AFTER_SEMICOLON = false;
    doTestSemicolon();
  }

  @Test
  public void testSpacingSemicolonBeforeTrue() {
    getSettings().SPACE_BEFORE_SEMICOLON = true;
    doTestSemicolon();
  }

  @Test
  public void testSpacingSemicolonBeforeFalse() {
    getSettings().SPACE_BEFORE_SEMICOLON = false;
    doTestSemicolon();
  }

  private void doTestSemicolon() {
    doTestSingleSource("spacingSemicolonInIterator");
  }

  @Test
  public void testSpacingTernaryBeforeQuestTrue() {
    getSettings().SPACE_BEFORE_QUEST = true;
    doTestTernary();
  }

  @Test
  public void testSpacingTernaryBeforeQuestFalse() {
    getSettings().SPACE_BEFORE_QUEST = false;
    doTestTernary();
  }

  @Test
  public void testSpacingTernaryAfterQuestTrue() {
    getSettings().SPACE_AFTER_QUEST = true;
    doTestTernary();
  }

  @Test
  public void testSpacingTernaryAfterQuestFalse() {
    getSettings().SPACE_AFTER_QUEST = false;
    doTestTernary();
  }

  @Test
  public void testSpacingTernaryAfterColonTrue() {
    getSettings().SPACE_AFTER_COLON = true;
    doTestTernary();
  }

  @Test
  public void testSpacingTernaryAfterColonFalse() {
    getSettings().SPACE_AFTER_COLON = false;
    doTestTernary();
  }

  @Test
  public void testSpacingTernaryBeforeColonTrue() {
    getSettings().SPACE_BEFORE_COLON = true;
    doTestTernary();
  }

  @Test
  public void testSpacingTernaryBeforeColonFalse() {
    getSettings().SPACE_BEFORE_COLON = false;
    doTestTernary();
  }

  private void doTestTernary() {
    doTestSingleSource("spacingTernary");
  }

  @Test
  public void testSpacingBeforeCommaTrue() {
    getSettings().SPACE_BEFORE_COMMA = true;
    doTestComma();
  }

  @Test
  public void testSpacingBeforeCommaFalse() {
    getSettings().SPACE_BEFORE_COMMA = false;
    doTestComma();
  }

  @Test
  public void testSpacingAfterCommaTrue() {
    getSettings().SPACE_AFTER_COMMA = true;
    doTestComma();
  }

  @Test
  public void testSpacingAfterCommaFalse() {
    getSettings().SPACE_AFTER_COMMA = false;
    doTestComma();
  }

  private void doTestComma() {
    doTestSingleSource("spacingComma");
  }

  private void doTestFormatOperators() {
    doTestSingleSource("spacingAroundOperators");
  }

  @Test
  public void testSpacingAroundAdditiveTrue() {
    getSettings().SPACE_AROUND_ADDITIVE_OPERATORS = true;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundAdditiveFalse() {
    getSettings().SPACE_AROUND_ADDITIVE_OPERATORS = false;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundAssignmentTrue() {
    getSettings().SPACE_AROUND_ASSIGNMENT_OPERATORS = true;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundAssignmentFalse() {
    getSettings().SPACE_AROUND_ASSIGNMENT_OPERATORS = false;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundBitwiseTrue() {
    getSettings().SPACE_AROUND_BITWISE_OPERATORS = true;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundBitwiseFalse() {
    getSettings().SPACE_AROUND_BITWISE_OPERATORS = false;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundEqualityTrue() {
    getSettings().SPACE_AROUND_EQUALITY_OPERATORS = true;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundEqualityFalse() {
    getSettings().SPACE_AROUND_EQUALITY_OPERATORS = false;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundLogicalTrue() {
    getSettings().SPACE_AROUND_LOGICAL_OPERATORS = true;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundLogicalFalse() {
    getSettings().SPACE_AROUND_LOGICAL_OPERATORS = false;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundMultiplicativeTrue() {
    getSettings().SPACE_AROUND_MULTIPLICATIVE_OPERATORS = true;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundMultiplicativeFalse() {
    getSettings().SPACE_AROUND_MULTIPLICATIVE_OPERATORS = false;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundRelationalTrue() {
    getSettings().SPACE_AROUND_RELATIONAL_OPERATORS = true;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundRelationalFalse() {
    getSettings().SPACE_AROUND_RELATIONAL_OPERATORS = false;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundShiftTrue() {
    getSettings().SPACE_AROUND_SHIFT_OPERATORS = true;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundShiftFalse() {
    getSettings().SPACE_AROUND_SHIFT_OPERATORS = false;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundUnaryTrue() {
    getSettings().SPACE_AROUND_UNARY_OPERATOR = true;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingAroundUnaryFalse() {
    getSettings().SPACE_AROUND_UNARY_OPERATOR = false;
    doTestFormatOperators();
  }

  @Test
  public void testSpacingBeforeCompoundLbraceTrue() {
    getSettings().SPACE_BEFORE_IF_LBRACE = true;
    doCompoundSpacingTest();
  }

  @Test
  public void testSpacingBeforeCompoundLbraceFalse() {
    getSettings().SPACE_BEFORE_IF_LBRACE = false;
    doCompoundSpacingTest();
  }

  @Test
  public void testSpacingBeforeElseTrue() {
    getSettings().SPACE_BEFORE_ELSE_KEYWORD = true;
    doCompoundSpacingTest();
  }

  @Test
  public void testSpacingBeforeElseFalse() {
    getSettings().SPACE_BEFORE_ELSE_KEYWORD = false;
    doCompoundSpacingTest();
  }

  private void doCompoundSpacingTest() {
    getCustomSettings().BRACE_STYLE_COMPOUND = SAME_LINE;
    getCustomSettings().ELSE_ON_NEW_LINE = false;
    doFormatTest();
  }

  @Test
  public void testSpacingBeforeTermBraceTrue() {
    getSettings().SPACE_BEFORE_DO_LBRACE = true;
    doFormatTest();
  }

  @Test
  public void testSpacingBeforeTermBraceFalse() {
    getSettings().SPACE_BEFORE_DO_LBRACE = false;
    doFormatTest();
  }

  @Test
  public void testSpacesWithinParenthesesTrue() {
    getSettings().SPACE_WITHIN_PARENTHESES = true;
    doFormatTest();
  }

  @Test
  public void testSpacesWithinParenthesesFalse() {
    getSettings().SPACE_WITHIN_PARENTHESES = false;
    doFormatTest();
  }

  @Test
  public void testSpacesAroundRangeTrue() {
    getCustomSettings().SPACE_AROUND_RANGE_OPERATORS = true;
    doFormatTest();
  }

  @Test
  public void testSpacesAroundRangeFalse() {
    getCustomSettings().SPACE_AROUND_RANGE_OPERATORS = false;
    doFormatTest();
  }

  @Test
  public void testSpacingConcatTrue() {
    getCustomSettings().SPACE_AROUND_CONCAT_OPERATOR = true;
    doFormatTest();
  }

  @Test
  public void testSpacingConcatFalse() {
    getCustomSettings().SPACE_AROUND_CONCAT_OPERATOR = false;
    doFormatTest();
  }

  @Test
  public void testSpacesWithinArrayTrue() {
    getCustomSettings().SPACES_WITHIN_ANON_ARRAY = true;
    doFormatTest();
  }

  @Test
  public void testSpacesWithinArrayFalse() {
    getCustomSettings().SPACES_WITHIN_ANON_ARRAY = false;
    doFormatTest();
  }

  @Test
  public void testSpacesWithinHashTrue() {
    getCustomSettings().SPACES_WITHIN_ANON_HASH = true;
    doFormatTest();
  }

  @Test
  public void testSpacesWithinHashFalse() {
    getCustomSettings().SPACES_WITHIN_ANON_HASH = false;
    doFormatTest();
  }

  @Test
  public void testSpaceAfterMyTrue() {
    getCustomSettings().SPACE_AFTER_VARIABLE_DECLARATION_KEYWORD = true;
    doFormatTest();
  }

  @Test
  public void testSpaceAfterMyFalse() {
    getCustomSettings().SPACE_AFTER_VARIABLE_DECLARATION_KEYWORD = false;
    doFormatTest();
  }

  @Test
  public void testMainFormatAsIs() {doTestMainFormat(WHATEVER);}

  @Test
  public void testMainFormatLong() {doTestMainFormat(FORCE);}

  @Test
  public void testMainFormatShort() {doTestMainFormat(SUPPRESS);}

  private void doTestMainFormat(int value) {
    getCustomSettings().MAIN_FORMAT = value;
    doFormatTest();
  }

  @Test
  public void testStatementModifierParensAsIs() {doTestStatementModifierParens(WHATEVER);}

  @Test
  public void testStatementModifierParensForce() {doTestStatementModifierParens(FORCE);}

  @Test
  public void testStatementModifierParensSuppress() {doTestStatementModifierParens(SUPPRESS);}

  private void doTestStatementModifierParens(int value) {
    getCustomSettings().OPTIONAL_PARENTHESES = value;
    doFormatTest();
  }


  @Test
  public void testSimpleDereferenceAsIs() {doTestSimpleDereference(WHATEVER);}

  @Test
  public void testSimpleDereferenceBraced() {doTestSimpleDereference(FORCE);}

  @Test
  public void testSimpleDereferenceUnbraced() {doTestSimpleDereference(SUPPRESS);}

  private void doTestSimpleDereference(int value) {
    getCustomSettings().OPTIONAL_DEREFERENCE_SIMPLE = value;
    doFormatTest();
  }


  @Test
  public void testHashRefElementAsIs() {doTestHashRefElementFormat(WHATEVER);}

  @Test
  public void testHashRefElementDoubleBuck() {doTestHashRefElementFormat(FORCE);}

  @Test
  public void testHashRefElementDereference() {doTestHashRefElementFormat(SUPPRESS);}

  private void doTestHashRefElementFormat(int value) {
    getCustomSettings().OPTIONAL_DEREFERENCE_HASHREF_ELEMENT = value;
    doFormatTest();
  }

  @Test
  public void testIndexesDereferenceAsIs() {doTestIndexesDereference(WHATEVER);}

  @Test
  public void testIndexesDereferenceForce() {doTestIndexesDereference(FORCE);}

  @Test
  public void testIndexesDereferenceSuppress() {doTestIndexesDereference(SUPPRESS);}

  private void doTestIndexesDereference(int value) {
    getCustomSettings().OPTIONAL_DEREFERENCE = value;
    doFormatTest();
  }

  @Test
  public void testQuoteHeredocOpenerAsIs() {doTestQuoteHeredocOpener(WHATEVER);}

  @Test
  public void testQuoteHeredocOpenerForce() {doTestQuoteHeredocOpener(FORCE);}

  @Test
  public void testQuoteHeredocOpenerSuppress() {doTestQuoteHeredocOpener(SUPPRESS);}

  private void doTestQuoteHeredocOpener(int value) {
    getCustomSettings().OPTIONAL_QUOTES_HEREDOC_OPENER = value;
    doFormatTest();
  }

  @Test
  public void testQuoteHashIndexAsIs() {doTestQuoteHashIndex(WHATEVER);}

  @Test
  public void testQuoteHashIndexForce() {doTestQuoteHashIndex(FORCE);}

  @Test
  public void testQuoteHashIndexSuppress() {doTestQuoteHashIndex(SUPPRESS);}

  private void doTestQuoteHashIndex(int value) {
    getCustomSettings().OPTIONAL_QUOTES_HASH_INDEX = value;
    doFormatTest();
  }

  @Test
  public void testUnquotePlusMinus() {doTestOptionalQuotation(SUPPRESS);}

  @Test
  public void testFatCommaAsIs() {doTestOptionalQuotation(WHATEVER);}

  @Test
  public void testFatCommaForce() {doTestOptionalQuotation(FORCE);}

  @Test
  public void testFatCommaSuppress() {doTestOptionalQuotation(SUPPRESS);}

  private void doTestOptionalQuotation(int value) {
    getCustomSettings().OPTIONAL_QUOTES = value;
    doFormatTest();
  }

  @Test
  public void testIssue1482() {
    doFormatTest();
  }

  @Test
  public void testCallArgumentsTrue() {
    getSettings().SPACE_WITHIN_METHOD_CALL_PARENTHESES = true;
    doFormatTest();
  }

  @Test
  public void testCallArgumentsFalse() {
    getSettings().SPACE_WITHIN_METHOD_CALL_PARENTHESES = false;
    doFormatTest();
  }

  @Test
  public void testSpacingInConditionFalse() {
    getSettings().SPACE_WITHIN_IF_PARENTHESES = false;
    doFormatTest();
  }

  @Test
  public void testSpacingInConditionTrue() {
    getSettings().SPACE_WITHIN_IF_PARENTHESES = true;
    doFormatTest();
  }

  @Test
  public void testSpacingInQwFalse() {
    getCustomSettings().SPACE_WITHIN_QW_QUOTES = false;
    doFormatTest();
  }

  @Test
  public void testSpacingInQwTrue() {
    getCustomSettings().SPACE_WITHIN_QW_QUOTES = true;
    doFormatTest();
  }


  @Test
  public void testSpacingBeforeConditionTrue() {
    getSettings().SPACE_BEFORE_IF_PARENTHESES = true;
    doFormatTest();
  }

  @Test
  public void testSpacingBeforeConditionFalse() {
    getSettings().SPACE_BEFORE_IF_PARENTHESES = false;
    doFormatTest();
  }


  @Test
  public void testExceptionClass() {doFormatTest();}

  @Test
  public void testIndentedHeredoc() {
    doFormatTest();
  }

  @Test
  public void testIndentedHeredocShiftLeft() {doFormatTest();}

  @Test
  public void testIndentedHeredocShiftLeftEmpty() {doFormatTest();}

  @Test
  public void testIndentedHeredocShiftLeftWithBadString() {doFormatTest();}

  @Test
  public void testIndentedHeredocShiftLeftWithLeadingNewLine() {doFormatTest();}

  @Test
  public void testIndentedHeredocShiftRight() {doFormatTest();}

  @Test
  public void testIndentedHeredocShiftRightEmpty() {doFormatTest();}

  @Test
  public void testIndentedHeredocShiftRightWithBadString() {doFormatTest();}

  @Test
  public void testIndentedHeredocShiftRightWithLeadingNewLine() {doFormatTest();}

  @Test
  public void testIssue1782() {doFormatTest();}

  @Test
  public void testChainedArrayOperation() {doFormatTest();}
}
