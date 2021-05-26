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

package unit.parser;


import org.junit.Test;
import unit.perl.parser.PerlParserTestBase;

public class TemplateToolkitParserTest extends PerlParserTestBase {
  public TemplateToolkitParserTest() {
    super("tt");
  }

  @Override
  protected String getBaseDataPath() {
    return "testData/unit/parser";
  }

  @Test
  public void testSetList() {
    doTest();
  }

  @Test
  public void testOperators() {
    doTest();
  }

  @Test
  public void testPrecedence() {
    doTest();
  }

  @Test
  public void testStrings() {
    doTest();
  }

  @Test
  public void testHash() {
    doTest();
  }

  @Test
  public void testSub() {
    doTest();
  }

  @Test
  public void testArray() {
    doTest();
  }

  @Test
  public void testVariables() {
    doTest();
  }

  @Test
  public void testChomp() {
    doTest();
  }

  @Test
  public void testComments() {
    doTest();
  }

  @Test
  public void testGet() {
    doTest();
  }

  @Test
  public void testCall() {
    doTest();
  }

  @Test
  public void testSet() {
    doTest();
  }

  @Test
  public void testDefault() {
    doTest();
  }

  @Test
  public void testInsert() {
    doTest();
  }

  @Test
  public void testInclude() {
    doTest();
  }

  @Test
  public void testProcess() {
    doTest();
  }

  @Test
  public void testBlockNamed() {
    doTest();
  }

  @Test
  public void testBlockNamedUnclosed() {
    doTest(false);
  }

  @Test
  public void testBlockAnon() {
    doTest();
  }

  @Test
  public void testBlockAnonUnclosed() {
    doTest(false);
  }

  @Test
  public void testWrapper() {
    doTest();
  }

  @Test
  public void testWrapperUnclosed() {
    doTest(false);
  }

  @Test
  public void testIf() {
    doTest();
  }

  @Test
  public void testIfUnclosed() {
    doTest(false);
  }

  @Test
  public void testSwitch() {
    doTest();
  }

  @Test
  public void testForeach() {
    doTest();
  }

  @Test
  public void testForeachUnclosed() {
    doTest(false);
  }

  @Test
  public void testWhile() {
    doTest();
  }

  @Test
  public void testWhileUnclosed() {
    doTest(false);
  }


  @Test
  public void testFilter() {
    doTest();
  }

  @Test
  public void testFilterPostfix() {
    doTest();
  }

  @Test
  public void testFilterUnclosed() {
    doTest(false);
  }

  @Test
  public void testThrow() {
    doTest();
  }

  @Test
  public void testUse() {
    doTest();
  }

  @Test
  public void testMacro() {
    doTest();
  }

  @Test
  public void testPerl() {
    doTest();
  }

  @Test
  public void testPerlPod() {
    doTest();
  }

  @Test
  public void testPerlUnclosed() {
    doTest(false);
  }

  @Test
  public void testTryCatch() {
    doTest();
  }

  @Test
  public void testTryCatchUnclosed() {
    doTest(false);
  }

  @Test
  public void testNext() {
    doTest();
  }

  @Test
  public void testLast() {
    doTest();
  }

  @Test
  public void testReturn() {
    doTest();
  }

  @Test
  public void testStop() {
    doTest();
  }

  @Test
  public void testClear() {
    doTest();
  }

  @Test
  public void testMeta() {
    doTest();
  }

  @Test
  public void testDebug() {
    doTest();
  }

  @Test
  public void testTags() {
    doTest();
  }

  @Test
  public void testChompMarkers() {
    doTest();
  }

  @Test
  public void testMultiDirectivesBlocks() {
    doTest();
  }

  @Test
  public void testIssue1262() {
    doTest();
  }

  @Test
  public void testIssue1263() {
    doTest();
  }
}
