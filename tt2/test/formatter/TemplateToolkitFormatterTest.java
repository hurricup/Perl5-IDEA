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


import base.TemplateToolkitLightTestCase;
import org.junit.Test;

public class TemplateToolkitFormatterTest extends TemplateToolkitLightTestCase {
  TemplateToolkitTestSettings myTestSettings;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    myTestSettings = new TemplateToolkitTestSettings(myFixture.getProject());
    myTestSettings.setUp();
  }

  @Override
  protected void tearDown() throws Exception {
    try {
      myTestSettings.tearDown();
    }
    finally {
      super.tearDown();
    }
  }

  @Override
  protected String getBaseDataPath() {
    return "testData/formatter";
  }

  @Override
  public String getFileExtension() {
    return "tt";
  }

  @Test
  public void testSetList() {
    doFormatTest();
  }

  @Test
  public void testArray() {
    doFormatTest();
  }

  @Test
  public void testBlockAnon() {
    doFormatTest();
  }

  @Test
  public void testBlockNamed() {
    doFormatTest();
  }

  @Test
  public void testCall() {
    doFormatTest();
  }

  @Test
  public void testChompMarkers() {
    doFormatTest();
  }

  @Test
  public void testClear() {
    doFormatTest();
  }

  @Test
  public void testDebug() {
    doFormatTest();
  }

  @Test
  public void testDefault() {
    doFormatTest();
  }

  @Test
  public void testExpressions() {
    doFormatTest();
  }

  @Test
  public void testFilter() {
    doFormatTest();
  }

  @Test
  public void testFilterPostfix() {
    doFormatTest();
  }

  @Test
  public void testForeach() {
    doFormatTest();
  }

  @Test
  public void testGet() {
    doFormatTest();
  }

  @Test
  public void testHash() {
    doFormatTest();
  }

  @Test
  public void testIf() {
    doFormatTest();
  }

  @Test
  public void testInclude() {
    doFormatTest();
  }

  @Test
  public void testInsert() {
    doFormatTest();
  }

  @Test
  public void testLast() {
    doFormatTest();
  }

  @Test
  public void testMacro() {
    doFormatTest();
  }

  @Test
  public void testMeta() {
    doFormatTest();
  }

  @Test
  public void testMultiDirectives() {
    doFormatTest();
  }

  @Test
  public void testNext() {
    doFormatTest();
  }

  @Test
  public void testPerl() {
    doFormatTest();
  }

  @Test
  public void testPerlPod() {
    doFormatTest();
  }

  @Test
  public void testProcess() {
    doFormatTest();
  }

  @Test
  public void testReturn() {
    doFormatTest();
  }

  @Test
  public void testSet() {
    doFormatTest();
  }

  @Test
  public void testStop() {
    doFormatTest();
  }

  @Test
  public void testSubCall() {
    doFormatTest();
  }

  @Test
  public void testSwitch() {
    doFormatTest();
  }

  @Test
  public void testThrow() {
    doFormatTest();
  }

  @Test
  public void testTryCatch() {
    doFormatTest();
  }

  @Test
  public void testUse() {
    doFormatTest();
  }

  @Test
  public void testWhile() {
    doFormatTest();
  }

  @Test
  public void testWrapper() {
    doFormatTest();
  }
}
